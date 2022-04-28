import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location} from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { GroupeDTO } from 'src/app/models/dto/groupeDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { CompanybusinessService } from 'src/app/_services/companybusiness.service';
import { GroupeService } from 'src/app/_services/groupe.service';
import Swal from 'sweetalert2';
import { GroupeAddComponent } from '../groupe-add/groupe-add.component';
import { GroupeViewComponent } from '../groupe-view/groupe-view.component';
import { CompanyBusinessDTO } from 'src/app/models/dto/companyBusinessDTO';
import { List } from 'lodash';

@Component({
  selector: 'app-groupe-list',
  templateUrl: './groupe-list.component.html',
  styleUrls: ['./groupe-list.component.css']
})
export class GroupeListComponent implements OnInit {
@Output()
groupeDTO !: GroupeDTO ;
 
  @ViewChild('groupeForm', { static: false })
  groupeForm!: FormGroup;
  groupeData !: GroupeDTO;
  groupe!:GroupeDTO[];
  searchKey!: string;
  showspinner = false;
  data : any ;
   element !: any;


   company: List<CompanyBusinessDTO>=[];
  datasource = new MatTableDataSource(this.groupe)
  
  displayedColumns: string[] = ['description', 'name', 'active','confirmed','deleted','actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
 id=this.route.snapshot.params['id'];
  message!: string;
  constructor(private dialog: MatDialog, private groupeService :GroupeService,private dialogService: DialogService, private companyService: CompanybusinessService,
    private notificationService: NotificationService,private route: ActivatedRoute, public router: Router, public _location: Location) {
    this.groupeData = {} as GroupeDTO;
  }
  //data sorting 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }


  ngOnInit(): void {
    this.groupeService.all().subscribe((response: any) => {
      this.datasource.data = response;
      
    });

    this.companyService.all().subscribe((response: any)=>{
    this.company=response ;
    })
    
  this.getBy(this.route.snapshot.paramMap.get('id'));

  }    

  


getBy(id:any) {
  this.groupeService.getByid(id)
    .subscribe(
      data => {
        this.element= data;
        console.log(data);
      },
      error => {
        console.log(error);
      });
}

  //search for data 

  onSearchClear() {
    this.searchKey = "";
    this.applyFilter();
  }

  applyFilter() {
    this.datasource.filter = this.searchKey.trim().toLowerCase();
  }


  // delete data 
  onDelete(id: number) {

    this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
      .afterClosed().subscribe((res: any) => {
        if (res) {
          this.groupeService.delete(id).subscribe(() => {
          })
          this.notificationService.success(' :: Deleted Successfully')
          this.refresh();
        }
      });
  }

        getone(){
        this.groupeService.getByid(this.id).subscribe((response)=>
        { this.data=response;
         this.groupe=this.data;
         console.log(this.groupe);
       })
      }


   View(row : any) { 
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "100%"; 
     this.dialog.open(GroupeViewComponent, {
          data: {
           name : row.name,
            description:row.description,
            active :row.active,
            confirmed:row.confirmed,
            deleted :row.deleted,
            companyBusiness:row.companyBusiness,
          },
        }
        ),dialogConfig

      }

  // create dialog config
  onCreate() {
    //this.companyService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(GroupeAddComponent, dialogConfig);

  }

  // edite dialogConfig
  onEdit(row: any) {
    this.companyService.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(GroupeAddComponent, dialogConfig);


  }

  // clear data 
  onClear() {
    this.groupeService.form.reset();
    this.groupeService.initializeFormGroup();
  }

  //refrech 
  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
      console.log(decodeURI(this._location.path()));
      this.router.navigate([decodeURI(this._location.path())]);
    });
  }


  // spinner from angular material
  spinner() {
    this.showspinner = true;
    setTimeout(() => { this.showspinner = false; }, 2000)
  }

  //alerte de confirmation 
  alertConfirmation() {
    Swal.fire({
      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        Swal.fire('Updated!', 'Company updated successfully.', 'success');
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelled');
      }
    });
  }

  
  
  updatePublished(status: any) {
    const data = {
      name: this.element.name,
      description: this.element.description,
      confirmed:this.element.confirmed,
      deleted: this.element.deleted,
      active: status
    };

    this.groupeService.updatestatus(this.element.id, data)
      .subscribe(
        response => {
          this.element.active= status;
          console.log(response);
        },
        error => {
          console.log(error);
        });
  }

}




