import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { Location } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { GroupeDTO } from 'src/app/models/dto/groupeDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { CompanybusinessService } from 'src/app/_services/companybusiness.service';
import { GroupeService } from 'src/app/_services/groupe.service';
import Swal from 'sweetalert2';
import { GroupeAddComponent } from '../groupe-add/groupe-add.component';
import { GroupeViewComponent } from '../groupe-view/groupe-view.component';
import { CompanyBusinessDTO } from 'src/app/models/dto/companyBusinessDTO';
import { List } from 'lodash';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-groupe-list',
  templateUrl: './groupe-list.component.html',
  styleUrls: ['./groupe-list.component.css']
})
/**
 * 
 * @author Tarchoun Abir
 *
 **/

export class GroupeListComponent implements OnInit {
  @Output()
  groupeDTO !: GroupeDTO;

  @ViewChild('groupeForm', { static: false })

  groupeData !: GroupeDTO;
  groupe : GroupeDTO[]= [];
  searchKey!: string;
  showspinner = false;
  currentGroupe: any;
  company: List<CompanyBusinessDTO> = [];
  datasource = new MatTableDataSource(this.groupe)
  displayedColumns: string[] = ['name', 'maxOperateur','groupStatus', 'createdDate', 'lastModifiedDate', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  message!: string;
  constructor(private _snackBar: MatSnackBar, private dialog: MatDialog, private groupeService: GroupeService, private companyService: CompanybusinessService,
  public router: Router, public _location: Location) {
    this.groupeData = {} as GroupeDTO;
  }


 /**
  * Data Sorting
  */
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }


  ngOnInit(): void {

    this.groupeService.getallGroupe().subscribe((response: any) => {
      this.datasource.data = response;
    });
    // and get companybussiness
    this.companyService.getAllCompanyBussiness().subscribe((response: any) => {
      this.company = response;
    })

  }
 /**********************
  *  getByIdGroupe
  */
  getByIdGroupe(id: any) {
    this.groupeService.getByidGroupe(id)
      .subscribe(
        data => {
          this.currentGroupe = data;
          console.log(data);
        },
        error => {
          console.log(error);
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
  onDeleteGroupe(id: number) {

    Swal.fire({
      title: 'Are you sure to delete this group !?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
    }).then((result) => {
      if (result.value) {
        this.groupeService.deleteGroupe(id)
          .subscribe(
            response => {
              console.log(response);
              Swal.fire('Deleted!','All Group  was Deleted successfully.','success');
              if (result.dismiss === Swal.DismissReason.cancel) {
              }
              // snackBar success 
              this._snackBar.open("Groupes Deleted Successfully",+ '' + "OK" + ''+ '⚡',{
                duration: 5000,
                horizontalPosition: "right",
                verticalPosition: "top",
                panelClass: ["mat-toolbar", "mat-success"],
              });
              
    
            this.refresh();
             },
              error => {
              // snackBar error
              this._snackBar.open("Error occurend !!" +error?.message, "",{
                duration: 3000,
                horizontalPosition: "right",
                verticalPosition: "top",
                panelClass: ["mat-toolbar", "mat-warn"],
              });
            });
          }
    });

  }

 /**********************
  *  View Details Groupe
  */
  ViewGroupe(row: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "100%";
    this.dialog.open(GroupeViewComponent, {
      data: {
        name: row.name,
        description: row.description,
        active: row.active,
        confirmed: row.confirmed,
        deleted: row.deleted,
        companyBusiness: row.companyBusiness,
      },
    }
    ), dialogConfig

  }
 /***************************
  *  Dialog Config For create 
  */
  onCreateGroupe() {
    this.groupeService.form.reset();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(GroupeAddComponent, dialogConfig);
    // this.datasource.data.push(result)

  }


 /***************************
  *  Dialog Config For Edit
  */
  onEditGroupe(row: any) {
    this.groupeService.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(GroupeAddComponent, dialogConfig);
    this.datasource.data.push(row)
  }

  /***************************
  * update Groupe Status
  */

  
  updateactiveGroupe(element: GroupeDTO) {
    Swal.fire({
      title: 'Are you sure to Update status  !?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes.',
      cancelButtonText: 'No.',
    }).then((result) => {
      if (result.value) {
        this.groupeService.updateGroupetByStatus(element.id, element.groupStatus).subscribe(res => {

          console.log(res);

          const index = this.datasource.data.indexOf(element);
          if (index > -1) {
            this.datasource.data[index].groupStatus = res.groupStatus;
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

 
  // clear data 
  onClear() {
    this.groupeService.form.reset();
    this.groupeService.initializeFormGroup();
  }

  //refrech 
  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
      console.log(decodeURI(this._location.path()));
      this.router.navigate([decodeURI(this._location.path())]);
    });
  }

   /*****************************
  *  iFiltring By Active Groupe

  active = "";
  searchByActiveGroupe() {
    this.groupeService.findByActiveGroupe(this.active)
      .subscribe(
        data => {

          this.datasource.filter = this.active.trim().toLowerCase();
          this.active.trim().toLowerCase() == data;
        },
        error => {
          console.log(error);
        });
  }
*/

}

