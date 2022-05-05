import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { GroupeDTO } from 'src/app/models/dto/groupeDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { CompanybusinessService } from 'src/app/_services/companybusiness.service';
import { GroupeService } from 'src/app/_services/groupe.service';
import Swal from 'sweetalert2';
import { GroupeAddComponent } from '../groupe-add/groupe-add.component';
import { GroupeViewComponent } from '../groupe-view/groupe-view.component';
import { CompanyBusinessDTO } from 'src/app/models/dto/companyBusinessDTO';
import { List } from 'lodash';
import { NgxSpinnerService } from "ngx-spinner";
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-groupe-list',
  templateUrl: './groupe-list.component.html',
  styleUrls: ['./groupe-list.component.css']
})
export class GroupeListComponent implements OnInit {
  @Output()
  groupeDTO !: GroupeDTO;

  @ViewChild('groupeForm', { static: false })

  groupeData !: GroupeDTO;
  groupe!: GroupeDTO[];
  searchKey!: string;
  showspinner = false;
  currentGroupe: any;
  groupes: any;
  result: any;
  company: List<CompanyBusinessDTO> = [];
  datasource = new MatTableDataSource(this.groupe)
  displayedColumns: string[] = [ 'name', 'groupStatus', 'createdDate', 'lastModifiedDate', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  id = this.route.snapshot.params['id'];
  message!: string;
  constructor(private _snackBar: MatSnackBar, private dialog: MatDialog, private groupeService: GroupeService, private dialogService: DialogService, private companyService: CompanybusinessService,
    private notificationService: NotificationService, private route: ActivatedRoute, public router: Router, public _location: Location) {
    this.groupeData = {} as GroupeDTO;
  }


  //data sorting 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }


  ngOnInit(): void {

      this.groupeService.getallGroupe().subscribe((response: any) => {
      this.datasource.data = response;});
      // and get companybussiness
      this.companyService.getAllCompanyBussiness().subscribe((response: any) => {
      this.company = response;
    })

  }

  getByGroupe(id: any) {
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

  //search for data 

  onSearchClear() {
    this.searchKey = "";
    this.applyFilter();
  }

  // fiter data 
  applyFilter() {
    this.datasource.filter = this.searchKey.trim().toLowerCase();

  }


  // delete data 
  onDeleteGroupe(id: number) {

    this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
      .afterClosed().subscribe((res: any) => {
        if (res) {
          this.groupeService.deleteGroupe(id).subscribe(() => {
          })
          this.refresh();
          this._snackBar.open(" :: Groupe have been deleted Successfully ", "", {
            duration: 3000,
            horizontalPosition: "right",
            verticalPosition: "top",
            panelClass: ["mat-toolbar", "mat-succes"],
          });

        }},
        error => {
          this._snackBar.open("Erroor occurend !!" + error?.message, "", {
            duration: 3000,
            horizontalPosition: "right",
            verticalPosition: "top",
            panelClass: ["mat-toolbar", "mat-warn"],
          });
        });

  }



  //view details groupe
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

  // create dialog config
  onCreateGroupe() {
    this.groupeService.form.reset();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(GroupeAddComponent, dialogConfig);
   // this.datasource.data.push(result)

  }

  // edite dialogConfig
  onEditGroupe(row: any) {
    this.groupeService.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(GroupeAddComponent, dialogConfig);
    this.datasource.data.push(row)
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

  //update status groupe
  updateactiveGroupe(element: GroupeDTO) {
    Swal.fire({
      title: 'Are you sure to Update status  !?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        this.groupeService.updateGroupeByStatus(element.id, element.groupStatus).subscribe( res => {
        
          console.log(res);
           
           const index = this.datasource.data.indexOf(element);
           if(index > -1) {
             this.datasource.data[index].groupStatus = res.groupStatus;
           }
        
          })
        Swal.fire('updated!', ' status updated successfully.', 'success');
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('TRY LATER...');
      }
    });

  }
  



  // delete all groupe
  removeAllGroupe() {
    Swal.fire({
      title: 'Are you sure to delete all Groupes  !?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        this.groupeService.deleteAllGroupe()
          .subscribe(
            response => {
              console.log(response);
               // snackBar success 
              this._snackBar.open("Groupes Deleted Successfully !!", "", {
                duration: 5000,
                horizontalPosition: "right",
                verticalPosition: "top",
                panelClass: ["mat-toolbar", "mat-succes"],
              });
              this.refresh();
            },

            error => {
               // snackBar error
              this._snackBar.open("Erroor occurend !!" + error?.message, "", {
                duration: 3000,
                horizontalPosition: "right",
                verticalPosition: "top",
                panelClass: ["mat-toolbar", "mat-warn"],
              });
            });
        Swal.fire('Deleted!', ' All Groupe  was Deleted successfully.', 'success');
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('TRY LATER...');
      }
    });

  }
  //filtring by active groupe 
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

}

