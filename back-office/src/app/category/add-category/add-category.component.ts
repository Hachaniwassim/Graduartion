import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { categoryDTO } from 'src/app/models/dto/categoryDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { Categoryservice } from 'src/app/_services/category.service';
import { Location } from '@angular/common';
import { MatDialogRef } from '@angular/material/dialog';

//make name file generated random 
let imageName = Math.random().toString() //+ ".png";

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
/*****
 * 
 * @author Tarchoun Abir
 * 
 */


export class AddCategoryComponent implements OnInit {

  listCategory: categoryDTO[] = [];
  imageFile: any;
  typeImage: any;
  isEditeMode = false;
  baseCategoryPath = "../../assets/igesa-software/images/Enterprise-" + localStorage.getItem('idEntreprise') + "/category/";

  constructor(public router: Router, public _location: Location,
    public categoryService: Categoryservice,
    public _Snackbar: MatSnackBar,
    public notificationService: NotificationService,
    public dialogService: DialogService, public dialogRef: MatDialogRef<AddCategoryComponent>) { }

  ngOnInit(): void {
    this.getCategoryByEntreprise();
  }


  //on select file 
  onSelectFile(file: any) {
    //console.log('file =======================>',file.target.files[0]);
    this.imageFile = file.target.files[0];
    this.typeImage = file.target.files[0].name.split('.').pop();
    console.log('file.target.files[0] :', file.target.files[0], "file.target.files[0].type :", file.target.files[0].name.split('.').pop());
  }

  /**
   * 
   * get category By enterprise 
   * 
   */

  getCategoryByEntreprise() {

    this.categoryService.getallCategorieByEntreprise().subscribe((res: any) => {
      console.log('result of the category====================>', res)
      this.listCategory = res;
      console.log('==================>res image', this.listCategory[0].image);

    }, (err: any) => { console.log('result of the category ====================>', err) })
  }



  // submit data with context EDITE : CREATE
  async onSubmit() {
   
    if (this.categoryService.form.valid) {
      console.log('validation')
      if (!this.categoryService.form.value.id) {
      
    
        
        let categoryBody = {
          image: this.baseCategoryPath + imageName + "." + this.typeImage,
          title: this.categoryService.form.value.title,
          description: this.categoryService.form.value.description,
          subtitle: this.categoryService.form.value.subtitle,
          menuimage: this.categoryService.form.value.menuimage,
          bannerimage: this.categoryService.form.value.bannerimage,
          createdDate: new Date(),
          lastModifiedDate: new Date(),
          enterpriseId: localStorage.getItem('idEntreprise'),

        };

        // upload categorie file
        await this.categoryService.uploadCategoryImage(imageName, this.imageFile).subscribe((res: any) => {
          
        });

        // create category file
         this.categoryService.createCategory(categoryBody).subscribe((res: any) => {
       
        },
          (err: any) => { console.log(' errr :: ===============>', err) });
        this.notificationService.success('  ::  ' + ' ' + 'add successfully ' + '⚡');
        this.refresh();
      }


      // update category file 
      else {

      
         let request=this.categoryService.form.value;
         request.image=this.categoryService.form.value.image;
         this.categoryService.updateCategory(request).subscribe((res) => {

          //this.notificationService.success('  ::  ' + ' ' + ' updated successfully ' + '⚡')
        },

          (err: any) => { console.log(' errr :: ===============>', err) });
      }
        
      this.onClose();
    }


  }




  // delete data 
  onDeleteCategory(id: number) {

    this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
      .afterClosed().subscribe((res: any) => {
        if (res) {
          this.categoryService.deleteCategory(id).subscribe((data: any) => {
            data = this.listCategory;
          })
          this.notificationService.success(' :: Deleted Successfully')

        }
        this.refresh();
      });
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


  onClear() {
    this.categoryService.form.reset();
    this.categoryService.initializeFormGroup();
  }
  // dialogue close 
  onClose() {
    this.categoryService.form.reset();
    this.categoryService.initializeFormGroup();
    this.dialogRef.close();
  }


  /********************
   * 
   * CKeditor Config
   * 
   */
  config = {
    height: 200,

    image: {
      // Configure the available styles.
      styles: [
        'alignLeft', 'alignCenter', 'alignRight'
      ],

      // Configure the available image resize options.
      resizeOptions: [
        {
          name: 'resizeImage:original',
          label: 'Original',
          value: null
        },
        {
          name: 'resizeImage:5',
          label: '5%',
          value: '5'
        },
        {
          name: 'resizeImage:10',
          label: '10%',
          value: '10'
        },
        {
          name: 'resizeImage:25',
          label: '25%',
          value: '25'
        },
        {
          name: 'resizeImage:50',
          label: '50%',
          value: '50'
        },
        {
          name: 'resizeImage:75',
          label: '75%',
          value: '75'
        }
      ],

      // You need to configure the image toolbar, too, so it shows the new style
      // buttons as well as the resize buttons.
      toolbar: [
        'imageStyle:alignLeft', 'imageStyle:alignCenter', 'imageStyle:alignRight',
        '|',
        'ImageResize',
        '|',
        'imageTextAlternative'
      ]
    },
    language: 'en'
  };




}
