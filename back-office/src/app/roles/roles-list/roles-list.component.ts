import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { RoleDTO } from 'src/app/models/dto/roleDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { CompanybusinessService } from 'src/app/_services/companybusiness.service';
import { RoleService } from 'src/app/_services/role.service';
import Swal from 'sweetalert2';
import { RolesAddComponent } from '../roles-add/roles-add.component';
import { RolesViewComponent } from '../roles-view/roles-view.component';
import { List } from 'lodash';
import { NgxSpinnerService } from "ngx-spinner";
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-roles-list',
  templateUrl: './roles-list.component.html',
  styleUrls: ['./roles-list.component.css']
})
export class RolesListComponent implements OnInit {

  @Output()
  groupeDTO !: RoleDTO;

  @ViewChild('groupeForm', { static: false })

  roleData !: RoleDTO;
  role!: RoleDTO[];
  searchKey!: string;
  showspinner = false;
  currentGroupe: any;
  roles: any;

  datasource = new MatTableDataSource(this.role)
  displayedColumns: string[] = [ 'name', 'groupStatus', 'createdDate', 'lastModifiedDate', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  id = this.route.snapshot.params['id'];
  message!: string;
  constructor(private _snackBar: MatSnackBar, private dialog: MatDialog, private roleService: RoleService, private dialogService: DialogService, private companyService: CompanybusinessService,
    private notificationService: NotificationService, private route: ActivatedRoute, public router: Router, public _location: Location) {
    this.roleData = {} as RoleDTO;
  }


  //data sorting 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }


  ngOnInit(): void {

      this.roleService.getallrole().subscribe((response: any) => {
      this.datasource.data = response;});
      // and get companybussiness
      this.companyService.getAllCompanyBussiness().subscribe((response: any) => {
    })
    //this.getBy(this.route.snapshot.paramMap.get('id'));

  }

  getByrole(id: any) {
    this.roleService.getByidrole(id)
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
  onDeleterole(id: number) {

    this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
      .afterClosed().subscribe((res: any) => {
        if (res) {
          this.roleService.deleterole(id).subscribe(() => {
          })
          
          this.ngOnInit();
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
  Viewrole(row: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "100%";
    this.dialog.open(RolesViewComponent, {
      data: {
        name: row.name,
      },
    }
    ), dialogConfig

  }

  // create dialog config
  onCreaterole() {
    this.roleService.form.reset();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(RolesAddComponent, dialogConfig);
//            this.datasource.data.push(result)

  }

  // edite dialogConfig
  onEditrole(row: any) {
    this.roleService.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(RolesAddComponent, dialogConfig);
  }

  // clear data 
  onClear() {
    this.roleService.form.reset();
    this.roleService.initializeFormGroup();
  }

  //refrech 
  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
      console.log(decodeURI(this._location.path()));
      this.router.navigate([decodeURI(this._location.path())]);
    });
  }

  /*/update status groupe
  updateactiveGroupe(element: RoleDTO) {

    this.roleService.updateroleByStatus(element.id, element.name).subscribe( res => {
        
         console.log(res);
          
          const index = this.datasource.data.indexOf(element);
          if(index > -1) {
            this.datasource.data[index].name = res.name;
          }
        },
      // snackBar error 
      );

  } */


  // delete all groupe
  removeAllrole() {
    Swal.fire({
      title: 'Are you sure to delete all Groupes  !?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        this.roleService.deleteAllrole()
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

              this.ngOnInit();
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
  searchByActiverole() {
    this.roleService.findByActiverole(this.active)
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
