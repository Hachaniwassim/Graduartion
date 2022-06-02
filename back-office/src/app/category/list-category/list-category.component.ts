
import{Location} from '@angular/common' 
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { categoryDTO } from 'src/app/models/dto/categoryDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { Categoryservice } from 'src/app/_services/category.service';
import Swal from 'sweetalert2';
import { AddCategoryComponent } from '../add-category/add-category.component';

@Component({
  selector: 'app-list-category',
  templateUrl: './list-category.component.html',
  styleUrls: ['./list-category.component.css']
})

/********
 * 
 * @author Tarchoun Abir
 * 
 */
export class ListCategoryComponent implements OnInit {
  listCategory:categoryDTO[]=[];
  imageFile:any;
  typeImage:any;
  showspinner=false;
  
  isEditeMode = false;
  baseCategoryPath="../../assets/igesa-software/images/Enterprise-"+localStorage.getItem('idEntreprise')+"/category/"
  constructor( public router: Router, public _location: Location,
     public categoryService : Categoryservice, 
     public _Snackbar:MatSnackBar,
      public notificationService:NotificationService,
     public  dialogService: DialogService,private dialog: MatDialog ) { }

  ngOnInit(): void {
    
    this.getCategoryByEntreprise();
  }


  //on select file 
  onSelectFile(file: any){
    //console.log('file =======================>',file.target.files[0]);
    this.imageFile = file.target.files[0] ;
    this.typeImage=file.target.files[0].name.split('.').pop();
    console.log('file.target.files[0] :',file.target.files[0] ,"file.target.files[0].type :",file.target.files[0].name.split('.').pop()); 
  }



     // delete data 
     onDeleteCategory(id: number) {
      
             Swal.fire({
              title: 'Are you sure to delete this category !?',
              icon: 'warning',
              showCancelButton: true,
              confirmButtonText: 'Yes',
              cancelButtonText: 'No',
            }).then((result) => {
              if (result.value) {
                this.categoryService.deleteCategory(id)
                  .subscribe(
                    response => {
                      console.log(response);
                      
                      Swal.fire('Deleted!','category deleted successfully.','success');
                      if (result.dismiss === Swal.DismissReason.cancel) {
                      }
                      
                    })
                  // snackBar success 
                  this._Snackbar.open(" Deleted Successfully",+ '' + "OK" + ''+ '⚡',{
                    duration: 5000,
                    horizontalPosition: "right",
                    verticalPosition: "top",
                    panelClass: ["mat-toolbar", "mat-success"],
                  });

                  this.getCategoryByEntreprise() 
                  this.listCategory.push()
                  this.refresh();
                
                  }
                 /** (err:any)=>{

                    this._Snackbar.open(" erreur ",+ '' + err.message + ''+ '⚡',{
                      duration: 5000,
                      horizontalPosition: "right",
                      verticalPosition: "top",
                      panelClass: ["mat-toolbar", "mat-success"],
                    });
                  }
                  */
              })
              
         }

    
    /**
     * 
     * get category By enterprise 
     * 
     */

   getCategoryByEntreprise (){
   
    this.spinner()
    this.categoryService.getallCategorieByEntreprise().subscribe((res:any)=>{
     console.log('result of the category====================>',res)
     this.listCategory=res;
     console.log('==================>res image',this.listCategory[0].image);
   },(err:any)=>{console.log('result of the category ====================>',err)})
 }

/**
 * 
 * 
 * refresh 
 * 
 */
 refresh(): void {
  this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
    console.log(decodeURI(this._location.path()));
    this.router.navigate([decodeURI(this._location.path())]);
  });
}




  // create dialog config
  onCreateCategory() { 
    this.categoryService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    dialogConfig.height= "80%";
    this.dialog.open(AddCategoryComponent, dialogConfig);

  }

  // edite dialogConfig
  onEditCategory(row: any) {
    console.log('======================id===>',this.categoryService.form.value.id);
    
    this.categoryService.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    dialogConfig.height= "80%";
    this.dialog.open(AddCategoryComponent, dialogConfig);


  }

  spinner(){ 
    this.showspinner=true;
    setTimeout(() => {this.showspinner=false;},2000)
  }
}
/*https://www.igesa.it/assets/common/images/favicon.png*/