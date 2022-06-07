import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { FormEntityDTO } from 'src/app/models/dto/formEntityDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { Contactservice } from 'src/app/_services/contact.service';
import {Location}from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-list-contacts',
  templateUrl: './list-contacts.component.html',
  styleUrls: ['./list-contacts.component.css']
})
export class ListContactsComponent implements OnInit {


  contact: FormEntityDTO[]= [];
  searchKey!: string;
  datasource = new MatTableDataSource(this.contact)
  displayedColumns: string[] = ['companyname', 'email','contactStatus', 'mobile','createdDate', 'lastModifiedDate', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  constructor(private _snackBar: MatSnackBar, private contactService: Contactservice,  public router: Router, public _location: Location) {
  }


 /**
  * Data Sorting
  */
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }


  ngOnInit(): void {

    this.contactService.getByEntreprise().subscribe((response: any) => {
      this.datasource.data = response;
    });

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

 /**********************
  *  Delete Groupe By Id
  */
  onDeleteContact(id: number) {

    Swal.fire({
      title: 'Are you sure to delete this contact !?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
    }).then((result) => {
      if (result.value) {
        this.contactService.deleteContact(id)
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
                  
      
        


  
  updateContactStatus(element: FormEntityDTO) {
    Swal.fire({
      title: 'Are you sure to Update status  !?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes.',
      cancelButtonText: 'No.',
    }).then((result) => {
      if (result.value) {
        this.contactService.updateContactByStatus(element.id, element.contactStatus).subscribe(res => {

          console.log(res);

          const index = this.datasource.data.indexOf(element);
          if (index > -1) {
            this.datasource.data[index].contactStatus = res.contactStatus;
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

 

  //refrech 
  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
      console.log(decodeURI(this._location.path()));
      this.router.navigate([decodeURI(this._location.path())]);
    });
  }


}



