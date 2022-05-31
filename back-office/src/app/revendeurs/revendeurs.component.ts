import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Location } from '@angular/common';
import { RevendeurDTO } from '../models/dto/revendeursDTO';
import { RevendeurService } from '../_services/revendeurs.services';

@Component({
  selector: 'app-revendeurs',
  templateUrl: './revendeurs.component.html',
  styleUrls: ['./revendeurs.component.css']
})
export class RevendeursComponent implements OnInit {

   

 
 /***********************
 * 
 * @author Tarchoun Abir
 * 
 */

  data !: RevendeurDTO;
  revendeur !: FormGroup;
  constructor(private fb: FormBuilder, private revendeurService:RevendeurService ,
    public router: Router, public _location: Location, public _snackBar: MatSnackBar,) {
 
  }
 

  ngOnInit() {
 
    /***************
     * Formcontrol
     */
    this.revendeur = this.fb.group({
      id: new FormControl(),
      title: new FormControl(''),
      description: new FormControl(''),
      createdDate: new FormControl(''),
      lastModifiedDate: new FormControl(''),
      entrepriseId: new FormControl(''),
      question:new FormControl(''),
      textbutton: new FormControl(''),
      

    });
 
    /***********************************
     * Get  By Entreprise 
     */
    this.revendeurService.getpageRevendeurByEntreprise().subscribe((res: RevendeurDTO[]) => {
      this.data = res[0];
      console.log("===============> data" ,this.data)
      this.revendeur.patchValue(this.data);
 
    });
  }
 
  /***********************
  * ***********
  *  update 
  * ***********
  */
 
  save() {
    Swal.fire({
         title: 'Are you sure to update this !?',
         icon: 'warning',
         showCancelButton: true,
         confirmButtonText: 'Yes',
         cancelButtonText: 'No',
         }).then((result) => {
         if (result.value) {
         this.revendeurService.updatePageRevendeur(this.revendeur.value).subscribe(r => {
          //test
          console.log(r);
          // snackBar success 
          this._snackBar.open("Updated Successfully", ' ' + "OK" +  ' ' +'⚡', {
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
 
 

    /************************************************************
   * 
   *  Config Ckeditor
   * 
   ***********************************************************/
 
     config = {
      height: 400,
   
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
   
 
    

 
  /**************************
   * 
   *  to allow upload image 
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
 
 
}
