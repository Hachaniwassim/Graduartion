import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { configgeneraleDTO } from '../models/dto/configgeneraleDTO';
import { ConfigGeneraleService } from '../_services/configgenerale.service';
import{Location}from '@angular/common';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-configurations',
  templateUrl: './configurations.component.html',
  styleUrls: ['./configurations.component.css']
})
export class ConfigurationsComponent implements OnInit {
  
/*********
 * 
 * @author Tarchoun Abir
 * 
 */
 data !: configgeneraleDTO;
 confiG !: FormGroup;
 configuration !: any ;
 constructor(private fb: FormBuilder, private ConfigGeneraleService: ConfigGeneraleService,
   public router: Router, public _location: Location, public _snackBar: MatSnackBar,) {

 }

 ngOnInit() {

   /***************
    * Formcontrol
    */
   this.confiG = this.fb.group({
     id: new FormControl(),
     createdDate: new FormControl(''),
     lastModifiedDate: new FormControl(''),
     phone: new FormControl(''),
     email: new FormControl(''),
     newslettersubtitle: new FormControl('') ,
     newslettertitle: new FormControl(''),
     fax: new FormControl(''),
     facebook: new FormControl(''),
     twitter: new FormControl(''),
     youtube: new FormControl(''),
     adresse:new FormControl(''),
     copyright : new FormControl(''),
     entrepriseId :localStorage.getItem('idEntreprise'),
   
   });

   /***********************************
    * Get privacy policy By Entreprise 
    */
   this.ConfigGeneraleService.getConfigByEntreprise().subscribe((res:any) => {
     this.data = res[0];
     this.confiG.patchValue(this.data);

   });
 }

 /***********************
 * ***********
 *  update 
 * ***********
 */

 save() {
   Swal.fire({
        title: 'Are you sure to update configuration !?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
        }).then((result) => {
        if (result.value) {
        this.ConfigGeneraleService.update(this.confiG.value).subscribe((r:any)=> {
         //test
         console.log(r);
         this.configuration= r;
         // snackBar success 
         this._snackBar.open("Updated Successfully", "OK" + '⚡', {
           duration: 5000,
           horizontalPosition: "right",
           verticalPosition: "top",
           panelClass: ["mat-toolbar", "mat-succes"],
         });
         Swal.fire('Updated!', ' Updated successfully.', 'success');
         if (result.dismiss === Swal.DismissReason.cancel) {
          
         }
         this.refresh();
       },

         error => {
           // snackBar error
           this._snackBar.open("Error occurend !!" + error?.message, "", {
             duration: 3000,
             horizontalPosition: "right",
             verticalPosition: "top",
             panelClass: ["mat-toolbar", "mat-warn"],
           });
         });
     }
     this.refresh();
   });
 }



 /**************************
  * 
  *  for allow uploaaad image 
  * 
  */
 public onReady(editor: any): void {
   if (editor.model.schema.isRegistered('image')) {
     editor.model.schema.extend('image', { allowAttributes: 'blockIndent' });
   }
 }

 //refrech 
 refresh(): void {
   this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
     console.log(decodeURI(this._location.path()));
     this.router.navigate([decodeURI(this._location.path())]);
   });
 }



  /************************************************************
  * 
  *  Config Ckeditor
  * 
  ***********************************************************/

   config = {
    height: 160,
 
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
