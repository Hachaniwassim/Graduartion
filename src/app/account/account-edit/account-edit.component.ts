import { MatTableDataSource } from '@angular/material/table';
import { Account } from 'src/app/models/account';
import { NotificationService } from 'src/app/shared/notification.service';
import { accountservice } from 'src/app/_services/account.service';
import Swal from 'sweetalert2';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { DialogService } from 'src/app/shared/dialog.service';

@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css']
})
export class AccountEditComponent implements OnInit {

  @ViewChild('accountForm', { static: false })
  accountForm!: FormGroup;
  accountData !: Account;
  account !: Account[];
  searchKey!: string;
  showspinner = false;
  datasource = new MatTableDataSource(this.account)
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;
  
     constructor(private dialog: MatDialog, private dialogService: DialogService, public Accountservice: accountservice, public dialogRef: MatDialogRef<AccountEditComponent>,
      private notificationService: NotificationService, private router: Router, public _location: Location) {
      this.accountData = {} as Account;
    }
  
  // sorting + pagination data 
  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }

  ngOnInit(): void {
    // sorting sorting and pagination data 
    this.datasource.sort = this.sort;
    this.datasource.paginator = this.paginator;
    this.Accountservice.all().subscribe((response: any) => {
      this.datasource.data = response;
    })
  }

  //clear data

  onClear() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
  }

  // submit data with context EDITE : DELETE
  onSubmit() {
    if (this.Accountservice.form.valid) {
      if (!this.Accountservice.form.get('id')?.value)
        this.Accountservice.create(this.Accountservice.form.value).subscribe(() => {
        })

      else(
        this.Accountservice.update(this.Accountservice.form.value).subscribe(() => {
        }))
         /*this.companyService.form.reset();
      this.companyService.initializeFormGroup();*/
      this.onClose();

    }
    this.refresh();

  }

  //refrech 

  refresh(): void {
    this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
      console.log(decodeURI(this._location.path()));
      this.router.navigate([decodeURI(this._location.path())]);
    });
  }

  // dialogue close 
  onClose() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
    this.dialogRef.close();
  }
}
