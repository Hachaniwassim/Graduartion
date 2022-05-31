import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Page2DTO } from 'src/app/models/dto/page2DTO';
import Swal from 'sweetalert2';
import { Location } from '@angular/common';
import { AssistanceService } from 'src/app/_services/assistanceService';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LinksDTO } from 'src/app/models/dto/linksDTO';
import { LinksService } from 'src/app/_services/links.service';


@Component({
  selector: 'app-usefulllinks',
  templateUrl: './usefulllinks.component.html',
  styleUrls: ['./usefulllinks.component.css']
})
export class UsefulllinksComponent implements OnInit {

  

 /***********************
 * 
 * @author Tarchoun Abir
 * 
 */

  data !:LinksDTO;
  liens !: FormGroup;
  constructor(private fb: FormBuilder, private lienService : LinksService ,
    public router: Router, public _location: Location, public _snackBar: MatSnackBar) {
 
  }
 
  /************************************************************
   * 
   *  Config Ckeditor
   * 
   ***********************************************************/
 
  config = {
    height: 700,
 
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
    this.liens = this.fb.group({
      id: new FormControl(),
      title: new FormControl(''),
      htmlContent: new FormControl(''),
      createdDate: new FormControl(''),
      lastModifiedDate: new FormControl(''),
      entrepriseId: new FormControl('')
    });
 
    /***********************************
     * Get privacy policy By Entreprise 
     */
    this.lienService.getLinksByEntreprise().subscribe((res: LinksDTO[]) => {
      this.data = res[0];
      this.liens.patchValue(this.data);
 
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
         this.lienService.updateLinksByEntreprise(this.liens.value).subscribe(r => {
          //test
          console.log(r);
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


