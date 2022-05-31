
import{Location} from '@angular/common' 
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { categoryDTO } from 'src/app/models/dto/categoryDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { Categoryservice } from 'src/app/_services/category.service';
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
  typeImage:any
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
  
      this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
        .afterClosed().subscribe((res: any) => {
          if (res) {
            this.categoryService.deleteCategory(id).subscribe((data:any) => {
             data=this.listCategory;
            })
            this.notificationService.success(' :: Deleted Successfully')
            this.refresh();
            }
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
  onEditCategory(id: any) {
    this.categoryService.populateForm(id);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    dialogConfig.height= "80%";
    this.dialog.open(AddCategoryComponent, dialogConfig);


  }

/*OnAddCategory(){
  this.addCategory();

}*/


//fo add an category resize
/*async addCategory(){
  let imageName=Math.random().toString() //+ ".png";
  let categoryBody={
    image:this.baseCategoryPath+imageName+"."+this.typeImage,
    title:this.categoryService.form.value.title,
    description:this.categoryService.form.value.description,
    subtitle:this.categoryService.form.value.subtitle,
    menuimage:this.categoryService.form.value.menuimage,
    bannerimage:this.categoryService.form.value.bannerimage,
    createdDate:new Date(),
    lastModifiedDate:new Date(),
    entrepriseId:localStorage.getItem('idEntreprise'),
  
  };


await this.categoryService.uploadCategoryImage(imageName,this.imageFile).subscribe((res:any)=>{
  console.log('the result of upload image ',res);
});
await this.categoryService.createCategory(categoryBody).subscribe((res:any)=>{
  console.log('the result of add category===>',res)
    },
    (err:any)=>{console.log(' errr :: ===============>',err)});
  }
*/
}