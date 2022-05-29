import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location}from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { EntrepriseDTO } from 'src/app/models/dto/entreprisDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { GroupeService } from 'src/app/_services/groupe.service';
import { EntrepriseService } from 'src/app/_services/entreprise.service';
import { EntrepriseViewComponent } from '../entreprise-view/entreprise-view.component';
import { EntrepriseAddComponent } from '../entreprise-add/entreprise-add.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-entreprise-list',
  templateUrl: './entreprise-list.component.html',
  styleUrls: ['./entreprise-list.component.css']
})


/**
 * 
 * @author Tarchoun Abir
 *
 **/



export class EntrepriseListComponent implements OnInit {
  @Output()
  entrepriseDTO!: EntrepriseDTO;
   
    @ViewChild('entrepriseForm', { static: false })
    entrepriseForm !:FormGroup;
    entrepriseData !:EntrepriseDTO;
    entreprise!:EntrepriseDTO[];
    searchKey!: string;
    showspinner = false;
    entreprises : any ;
    datasource = new MatTableDataSource(this.entreprise)
    id=this.route.snapshot.params['id'];
    message!: string;
    displayedColumns: string[] = ['companyname','email','phone','codefiscale','createdDate','lastModifiedDate','actions'];

    @ViewChild(MatPaginator) paginator!: MatPaginator;
    @ViewChild(MatSort, {}) sort!: MatSort;
     response: any;
    constructor(private dialog: MatDialog,private entrepriseService :EntrepriseService ,private groupeService :GroupeService,private dialogService: DialogService,
      private notificationService: NotificationService,private route: ActivatedRoute, public router: Router, public _location: Location) {
      this.entrepriseData = {} as EntrepriseDTO;
    }
  
    
    //data sorting 
    ngAfterViewInit() {
      this.datasource.paginator = this.paginator;
      this.datasource.sort = this.sort;
    }
  
  
    ngOnInit(): void {
      this.datasource.paginator = this.paginator;
      this.datasource.sort = this.sort;
      this.getAllEntreprise();
    
    }    


    getAllEntreprise(){

        this.entrepriseService.getAllEntreprise().subscribe((response: any) => {
        this.datasource.data = response;
        
      });
    }
  
    //search for data 
  
    onSearchClear() {
      this.searchKey = "";
      this.applyFilter();
    }
  
    // filter data 
    applyFilter() {
      this.datasource.filter = this.searchKey.trim().toLowerCase();
     
    }
  
  
    // delete data 
    onDeleteEntreprise(id: number) {
  
      this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
        .afterClosed().subscribe((res: any) => {
          if (res) {
            this.entrepriseService.deleteEntreprise(id).subscribe((data) => {
              data = this.response;
              this.entreprise.push(this.response);
            })
            this.notificationService.success(' :: Deleted Successfully')
            
          }
         this.refresh();
        });
    }
  
  
  
    //view details entreprise
     ViewEntreprise(row : any) { 
      this.entrepriseService.populateForm(row);
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      dialogConfig.width = "60%";
      dialogConfig.position = {  
          'top': '100px',    
      }; 
       this.dialog.open(EntrepriseViewComponent, {
            data: {
              companyname : row.companyname,
              phone :row.phone,
              fax:row.fax,
              note :row.note,
              codefiscale:row.codefiscale,
              email :row.email,
              adresse :row.adresse,
              refrente :row.refrente,
              companyBusinessId :row.companyBusinessId,
              street :row.street,
              city :row.city,
              goupeId:row.groupeId,
            },
          }
       )
  
        }
  
    // create dialog config
    onCreateEntreprise() { 
      this.entrepriseService.initializeFormGroup();
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      dialogConfig.width = "60%";
      this.dialog.open(EntrepriseAddComponent, dialogConfig);

  
    }
  
    // edite dialogConfig
    onEditEntreprise(row: any) {
      this.entrepriseService.populateForm(row);
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      dialogConfig.width = "60%";
      this.dialog.open(EntrepriseAddComponent, dialogConfig);
  
  
    }
  
    // clear data 
    onClear() {
      this.entrepriseService.form.reset();
      this.entrepriseService.initializeFormGroup();
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
}
