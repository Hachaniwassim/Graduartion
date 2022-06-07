import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { productsDTO } from '../models/dto/productsDTO';
import { DialogService } from '../shared/dialog.service';
import { NotificationService } from '../shared/notification.service';
import { ProductService } from '../_services/products.service';
import{Location}from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
 listProduct : productsDTO[]=[];
  constructor(public productService:ProductService, public dialogService: DialogService,public router: Router, public _location: Location, public notificationService :NotificationService, public _Snackbar: MatSnackBar) { 

  }
  //public linkProductImage="../../assets/igesa-software/images/Enterprise-"+localStorage.getItem('idEntreprise')+"/product/"
  listPrducts:any;
  tagsByEntreprise:any
  categoryByEntreprise:any;
  imageFile:any;
  typeImage:any;
  isEditeMode=false;
  baseProductPath="../../assets/igesa-software/images/Enterprise-"+localStorage.getItem('idEntreprise')+"/product/";
  //selected items 
  categorieSelected:any;
  tagSelected:any;
  idProduct:any;
  ngOnInit(): void {
    this.getTagsByEntreprise();
    this.getCategorieByEntreprise();
    this.getProductsByEntreprise ();
    this.productService.populateForm(this.idProduct)
  }
  getTagsByEntreprise(){
    this.productService.getTagsByEntreprise().subscribe((res:any)=>{
      console.log("tags===>",res);
      this.tagsByEntreprise=res;
    });
    }
    getCategorieByEntreprise(){
      this.productService.getCategorieByEntreprise().subscribe((res:any)=>{
        console.log("categorie===>",res);
        this.categoryByEntreprise=res;
      });
      }


//events 
onChangeCategorie(event:any){
  this.categorieSelected=event.target.value;
  console.log('the categorie selected =>',this.categorieSelected)
}
onChangeTag(event:any){
console.log('the selected tags ====>',event);
this.tagSelected=event;
}

onSelectFile(file: any){
  
  //test="../../assets/igesa-software/images/Enterprise-2/product/0.6628314392580117.png"  

  //console.log('file =======================>',file.target.files[0]);
  this.imageFile = file.target.files[0] ;
  this.typeImage=file.target.files[0].name.split('.').pop();
  console.log('file.target.files[0] :',file.target.files[0] ,"file.target.files[0].type :",file.target.files[0].name.split('.').pop()); 
}
 onAddProduct(){
 this.addProduct();
}


//fo add an produit resize
async addProduct(){
    let imageName=Math.random().toString() //+ ".png";
    let productBody={
    image:this.baseProductPath + imageName + "." + this.typeImage,
    title:this.productService.form.value.title,
    description:this.productService.form.value.description,
    caracteristique:this.productService.form.value.caracteristique,
    slug:this.productService.form.value.slug,
    name:this.productService.form.value.name,
    requirements:this.productService.form.value.requirements,
    categorieId:this.categorieSelected,
    createdDate:new Date(),
    lastModifiedDate:new Date(),
    entrepriseId:localStorage.getItem('idEntreprise'),
  };


await this.productService.uploadProductImage(imageName,this.imageFile).subscribe((res:any)=>{
  console.log('the result of upload image ',res);
});

await this.productService.addProduct(productBody).subscribe((res:any)=>{
  console.log('the result of add product ===>',res)
  this.idProduct=res.id
  console.log('idProduct======================>',this.idProduct)
  
  for(let i=0;i<this.tagSelected.length;i++){
     this.productService.assignTagsToAnProduct(this.idProduct,this.tagSelected[i]).subscribe((res:any)=>{
      console.log('the result of assign product ===>',res)
      
    },(err:any)=>{console.log('assign errr===============>',err)});
  }
});

}



   getProductsByEntreprise (){
     this.productService.getAllProductsByEntreprise().subscribe((res:any)=>{
      console.log('result of the product ====================>',res)
      this.listPrducts=res;
     /*console.log('==================>res image',this.listPrducts[0].image);*/ },
    (err:any)=>{console.log('result of the product ====================>',err)})
  }

  /*************************
   * delete product
   */
     onDeleteProduct(id: number) {
              Swal.fire({
                title: 'Are you sure to delete this product !?',
                icon: 'warning',
                showCancelButton: true ,
                confirmButtonText: 'Yes',
                cancelButtonText: 'No',
                cancelButtonColor: 'gray'
              }).then((result) => {
                if (result.value) {
                  this.productService.deleterPoducts(id)
                    .subscribe(
                      response => {
                        console.log(response);
                        
                        Swal.fire('Deleted!','All Groupe  was Deleted successfully.','success');
                        if (result.dismiss === Swal.DismissReason.cancel) {
                        }
                        
                       this.refresh();
                       this.getProductsByEntreprise();
                       this.listProduct.push();
                       
                      });
                       // snackBar success 
                       this._Snackbar.open("Groupes Deleted Successfully",+ '' + "OK" + ''+ '⚡',{
                        duration: 5000,
                        horizontalPosition: "right",
                        verticalPosition: "top",
                        panelClass: ["mat-toolbar", "mat-success"],
                      });
                    }
                    
       this.refresh();
             this.getProductsByEntreprise();
             this.listProduct.push();
                  
              });
              
    }
  
 
 //refrech 
 refresh(): void {
  this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
    console.log(decodeURI(this._location.path()));
    this.router.navigate([decodeURI(this._location.path())]);
  });
}

  onEdite(row : any){
    this.isEditeMode = true;
    this.productService.populateForm(row);
  }


   onUpdate(){
      this.isEditeMode != this.isEditeMode
      let request =this.productService.form.value;
      request.image=this.productService.form.value.image;
       this.productService.update(request).subscribe((res) => {
        console.log("====================> updateeeeee test ", res)

      this.notificationService.success('  ::  ' + ' ' + ' updated successfully ' + '⚡')
      this.refresh();
    })
      

  }


  //CKeditor
   
  config = {
    height: 250, 
    
    image: {
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
