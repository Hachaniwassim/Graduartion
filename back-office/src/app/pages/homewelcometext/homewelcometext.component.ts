import { Component, OnInit } from '@angular/core';
import { Location}from '@angular/common';
import { Page2DTO } from 'src/app/models/dto/page2DTO';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { HomeService } from 'src/app/_services/homeService';
import { Page3DTO } from 'src/app/models/dto/page3DTO';

@Component({
  selector: 'app-homewelcometext',
  templateUrl: './homewelcometext.component.html',
  styleUrls: ['./homewelcometext.component.css']
})
export class HomewelcometextComponent implements OnInit {

 /***********************
 * 
 * @author Tarchoun Abir
 * 
 */

  data !: Page3DTO;
  home !: FormGroup;
  constructor(private fb: FormBuilder, private homeService: HomeService ,
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
    this.home = this.fb.group({
      id: new FormControl(),
      title: new FormControl(''),
      htmlContent: new FormControl(''),
      createdDate: new FormControl(''),
      lastModifiedDate: new FormControl(''),
      entrepriseId: new FormControl('')
    });
 
    /***********************************
     * Get  By Entreprise 
     */
    this.homeService.getPagesByCurrentEntreprise().subscribe((res: Page3DTO[]) => {
      this.data = res[0];
      console.log("===============> data" ,this.data)
      this.home.patchValue(this.data);
 
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
         this.homeService.update(this.home.value).subscribe(r => {
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
 
 