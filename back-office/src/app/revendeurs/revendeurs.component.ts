import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Location } from '@angular/common';
import { RevendeurDTO } from '../models/dto/revendeursDTO';
import { RevendeurService } from '../_services/revendeurs.services';
import { PostRevendeurService } from '../_services/post-revendeurs.service';
import { PostRevendeurDTO } from '../models/dto/post-revebdeurDTO';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

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
  contact: PostRevendeurDTO[]= [];
  searchKey!: string;
  datasource = new MatTableDataSource(this.contact)
  displayedColumns: string[] = ['companyname', 'email','revendeursStatus', 'mobile','createdDate', 'lastModifiedDate', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  constructor(private fb: FormBuilder, private revendeurService:RevendeurService ,
    public router: Router, public _location: Location, public _snackBar: MatSnackBar,public revendeurPostService: PostRevendeurService) {
 
  }
 
   /**
  * Data Sorting
  */
    ngAfterViewInit() {
      this.datasource.paginator = this.paginator;
      this.datasource.sort = this.sort;
    }


    

  /********************
   *   search Clear 
   */
  onSearchClear() {
    this.searchKey = "";
    this.applyFilter();
  }


 /**********************
  *  Filter Data
  */
  applyFilter() {
    this.datasource.filter = this.searchKey.trim().toLowerCase();

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
    
    this.revendeurPostService.getByEntreprise().subscribe((response: any) => {
      this.datasource.data = response;
  })
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







/**********************
  *  Delete Groupe By Id
  */
 onDeleteRevendeur(id: number) {

  Swal.fire({
    title: 'Are you sure to delete this contact !?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Yes',
    cancelButtonText: 'No',
  }).then((result) => {
    if (result.value) {
      this.revendeurPostService.deleteRevendeur(id)
        .subscribe(
          response => {
            console.log(response);
            this.contact.push(response)
               Swal.fire('deleted!', ' deletd successfully.', 'success');
               if (result.dismiss === Swal.DismissReason.cancel) {}
              })

            }
             // snackBar success 
             this._snackBar.open(" deleted successfully", "OK" + '⚡', {
              duration: 5000,
              horizontalPosition: "right",
              verticalPosition: "top",
              panelClass: ["mat-toolbar", "mat-succes"],
            });
         })
    
  }
                
    
      



updateRevendeurStatus(element: PostRevendeurDTO) {
  Swal.fire({
    title: 'Are you sure to Update status  !?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Yes.',
    cancelButtonText: 'No.',
  }).then((result) => {
    if (result.value) {
      this.revendeurPostService.updateRevendeurByStatus(element.id, element.revendeursStatus).subscribe(res => {

        console.log(res);

        const index = this.datasource.data.indexOf(element);
        if (index > -1) {
          this.datasource.data[index].revendeursStatus = res.revendeursStatus;
        }
         // snackBar success 
       this._snackBar.open("status updated Successfully",+ ' '+  "OK"+ ' '+'⚡', {
        duration: 5000,
        horizontalPosition: "right",
        verticalPosition: "top",
        panelClass: ["mat-toolbar", "mat-succes"],
      });

      })
      Swal.fire('updated!', ' status updated successfully.', 'success');
    } else if (result.dismiss === Swal.DismissReason.cancel) {
    }
      
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

  /***************************************** list-revendeurs  *********************************************/
 
 
}
