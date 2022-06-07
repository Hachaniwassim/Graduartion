import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountDTO } from 'src/app/models/dto/accountDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { Accountservice } from 'src/app/_services/account.service';
import { Location } from '@angular/common';
import { AccountEditComponent } from '../account-edit/account-edit.component';
import Swal from 'sweetalert2';
import { AddAccountComponent } from '../add-account/add-account.component';

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css'],
})
export class AccountListComponent implements OnInit {
  @ViewChild('companyForm', { static: false })
  accountForm!: FormGroup;
  accountData!: AccountDTO;
  account: AccountDTO[] = [];
  searchKey!: string;
  showspinner = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  data: any;
  datasource = new MatTableDataSource(this.account);
  displayedColumns: string[] = [
    'username',
    'email',
    'fiscaleCode',
    'role',
    'accountStatus',
    'createdDate',
    'lastModifiedDate',
    'actions',
  ];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  id = this.route.snapshot.params['id'];
  mybreakpoint!: number;

  constructor(
    private dialog: MatDialog,
    private dialogService: DialogService,
    private Accountservice: Accountservice,
    private route: ActivatedRoute,
    public router: Router,
    public _location: Location,
    public dialogRef: MatDialogRef<AddAccountComponent>
  ) {
    this.accountData = {} as AccountDTO;
  }

  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }

  ngOnInit(): void {
    this.mybreakpoint = window.innerWidth <= 600 ? 1 : 6;
    this.datasource.sort = this.sort;
    this.datasource.paginator = this.paginator;
    this.getAll();
  }

  getAll() {
    this.Accountservice.all().subscribe((response) => {
      this.datasource.data = response;
      console.log('getAll : response ', response);
    });
  }
  onSearchClear() {
    this.searchKey = '';
    this.applyFilter();
  }

  applyFilter() {
    this.datasource.filter = this.searchKey.trim().toLowerCase();
  }
 
 
  onDelete(id: number) {
    this.dialogService
      .openConfirmDialog('Are you sure to delete this record ?')
      .afterClosed()
      .subscribe((res: any) => {
        if (res) {
          this.Accountservice.delete(id).subscribe(() => {});
          this.getAll();
          this.refresh();
        }
      });
  }

  //refrech
  refresh(): void {
    this.router
      .navigateByUrl('/refresh', { skipLocationChange: true })
      .then(() => {
        console.log(decodeURI(this._location.path()));
        this.router.navigate([decodeURI(this._location.path())]);
      });
  }

  onEdit(row: any) {
    this.Accountservice.populateForm(row);
    console.log('the row ===>', JSON.stringify(row));
    console.log("row.groupeId  =>",row.groupeId)
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    // dialogConfig.user_id = row.id;
    dialogConfig.width = '60%';
    dialogConfig.data = {
      id: row.id,
      groupeId: row.groupeId,
      roles: JSON.stringify(row.roles),
    };
    
    this.dialog.open(AccountEditComponent, dialogConfig);
  }

  onClear() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
  }
  //update status 
  updateaStatusAccount(element: AccountDTO) {
    Swal.fire({
      title: 'Are you sure to Update status  !?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes.',
      cancelButtonText: 'No.',
    }).then((result) => {
      if (result.value) {
        this.Accountservice.updateAccountByStatus(
          element.id,
          element.accountStatus
        ).subscribe((res) => {
          console.log(res);

          const index = this.datasource.data.indexOf(element);
          if (index > -1) {
            this.datasource.data[index].accountStatus = res.accountStatus;
          }
        });
        Swal.fire('updated!', ' status updated successfully.', 'success');
      } else if (result.dismiss === Swal.DismissReason.cancel) {
      }
    });
  }


   /***************************
  *  Dialog Config For create 
  */
    onCreate() {
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      dialogConfig.width = "60%";
      this.dialog.open(AddAccountComponent, dialogConfig);
      // this.datasource.data.push(result)
  
    }
    
}
