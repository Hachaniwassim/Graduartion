import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { Privacyservice } from '../_services/privacy.service';
import { privacyDTO } from '../models/dto/privacyDTO';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
// custom config to allow upload of images and resize it using Ckeditor 5 (   < = Angular 9)
//import * as customBuild from 'src/app/config/ckCustomBuild/build/ckeditor.js'

@Component({
  selector: 'app-privacypolicy',
  templateUrl: './privacypolicy.component.html',
  styleUrls: ['./privacypolicy.component.css']
})

/*********
 * 
 * @author Tarchoun Abir
 * 
 */
export class PrivacypolicyComponent implements OnInit {

  data !: privacyDTO;
  privacy !: FormGroup;
  constructor(private fb: FormBuilder, private privacyService: Privacyservice,
    public router: Router, public _location: Location, public _snackBar: MatSnackBar,) {

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


  ngOnInit() {

    /***************
     * Formcontrol
     */
    this.privacy = this.fb.group({
      id: new FormControl(),
      title: new FormControl(''),
      htmlContent: new FormControl(''),
      createdDate: new FormControl(''),
      lastModifiedDate: new FormControl(''),
      entrepriseId: localStorage.getItem('idEntreprise'),
    });

    /***********************************
     * Get privacy policy By Entreprise 
     */
    this.privacyService.getPrivacyByEntreprise().subscribe((res: privacyDTO[]) => {
      this.data = res[0];
      this.privacy.patchValue(this.data);

    });
  }

  /***********************
  * ***********
  *  update 
  * ***********
  */

  save() {
    Swal.fire({
         title: 'Are you sure to update Privacy Policy  !?',
         icon: 'warning',
         showCancelButton: true,
         confirmButtonText: 'Yes',
         cancelButtonText: 'No',
         }).then((result) => {
         if (result.value) {
         this.privacyService.update(this.privacy.value).subscribe(r => {
          //test
          console.log(r);
          // snackBar success 
          this._snackBar.open("updated successfully", "OK" + '⚡', {
            duration: 5000,
            horizontalPosition: "right",
            verticalPosition: "top",
            panelClass: ["mat-toolbar", "mat-succes"],
          });
          Swal.fire('updated!', ' updated successfully.', 'success');
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





}

