import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { categoryDTO } from '../models/dto/categoryDTO';
import { DialogService } from '../shared/dialog.service';
import { NotificationService } from '../shared/notification.service';
import { Categoryservice } from '../_services/category.service';
import {Location}from '@angular/common';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  listCategory:categoryDTO[]=[];
  imageFile:any;
  typeImage:any
  baseCategoryPath="../../assets/igesa-software/images/Enterprise-"+localStorage.getItem('idEntreprise')+"/category/"
  constructor( public router: Router, public _location: Location,
     public categoryService : Categoryservice, 
     public _Snackbar:MatSnackBar,
      public notificationService:NotificationService,
     public  dialogService: DialogService ) { }

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
  
      this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
        .afterClosed().subscribe((res: any) => {
          if (res) {
            this.categoryService.deleteCategory(id).subscribe((data:any) => {
             data=this.listCategory;
            })
            this.notificationService.success(' :: Deleted Successfully')
            
          }
         this.refresh();
        });
    }
  

    
    /**
     * 
     * get category By enterprise 
     * 
     */

   getCategoryByEntreprise (){
   
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
  
  
}
