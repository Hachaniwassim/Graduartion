import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { plateforomeDTO } from 'src/app/models/dto/plateformeDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { PlateformeService } from 'src/app/_services/plateforme.service';
import Swal from 'sweetalert2';
import { PlateformeAddComponent } from '../plateforme-add/plateforme-add.component';
import { PlateformeViewComponent } from '../plateforme-view/plateforme-view.component';


@Component({
  selector: 'app-plateforme-list',
  templateUrl: './plateforme-list.component.html',
  styleUrls: ['./plateforme-list.component.css']
})
export class PlateformeListComponent implements OnInit {

  @ViewChild('companyForm', { static: false })
  companyForm!: FormGroup;
  companyData !: plateforomeDTO;
  companybusiness!: plateforomeDTO[];
  searchKey!: string;
  showspinner = false;
  data : any ;
  
  plateformee={
   image:"", 
   email: "",
   phone: "",
   adresse: "",
   published: "",
  }

  datasource = new MatTableDataSource(this.companybusiness)
  displayedColumns: string[] = ['image', 'email','phone','adresse','published', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
 id=this.route.snapshot.params['id'];
  constructor(private dialog: MatDialog, private dialogService: DialogService, private plateformeservice: PlateformeService,
    private notificationService: NotificationService,private route: ActivatedRoute, public router: Router, public _location: Location) {
    this.companyData = {} as plateforomeDTO;
  }
  //data sorting 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }


  ngOnInit(): void {
    //data sorting and pagination from angular materila 
    this.plateformeservice.getAllplateformes().subscribe((response: any) => {
      this.datasource.data = response;
      this.datasource.paginator = this.paginator;
      this.datasource.sort = this.sort;
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
  onDeleteCompany(id: number) {

    this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
      .afterClosed().subscribe((res: any) => {
        if (res) {
          this.plateformeservice.deleteplateforme(id).subscribe(() => {
            //filter sur l objet  optionnel

           /*this.datasource.data = this.datasource.data.filter((o: any) => {
              return o.id !== id ? o : false;
            })
            console.log(this.datasource.data);*/
          })
          this.notificationService.success(' :: Deleted Successfully')
          this.refresh();
        }
      });
  }

        getone(){
        this.plateformeservice.getByidplateforme(this.id).subscribe((response)=>
        { this.data=response;
         this.plateformee=this.data;
         console.log(this.plateformee);
       })
      }


   ViewCompany(row : any) { 
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "100%"; 
     this.dialog.open(PlateformeViewComponent, {
          data: {
            image : row.image,
            email:row.email,
            phone : row.phone,
            adresse : row.adresse,
            published : row.published,
          },
        }
        ),dialogConfig

      }

  // create dialog config
  onCreateCompany() {
    //this.companyService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(PlateformeAddComponent, dialogConfig);

  }

  // edite dialogConfig
  onEditCompany(row: any) {
    this.plateformeservice.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(PlateformeAddComponent, dialogConfig);


  }

  // clear data 
  onClear() {
    this.plateformeservice.form.reset();
    this.plateformeservice.initializeFormGroup();
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
}
