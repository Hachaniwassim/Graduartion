import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { AccountDTO } from 'src/app/models/dto/accountDTO';
import { NotificationService } from 'src/app/shared/notification.service';
import { Accountservice } from 'src/app/_services/account.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css']
})
export class AccountEditComponent implements OnInit {

  account !: AccountDTO[];
  datasource = new MatTableDataSource(this.account);
  constructor( public Accountservice : Accountservice  ,private notificationagencyService : NotificationService,public dialogRef: MatDialogRef<AccountEditComponent>) { }

  ngOnInit(): void {
  }

  getAll() {
    this.Accountservice.all().subscribe((response: any) => {
      this.datasource.data = response;
    });

  }

  onClear() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
  }

  onSubmit() {
    if (this.Accountservice.form.valid) {
      if ( ! this.Accountservice.form.get('id')?.value)
        this.Accountservice.create(this.Accountservice.form.value).subscribe(() => {
          this.getAll();
          this.notificationagencyService.success(':: Submitted successfully');
        })
      else
      this.Accountservice.update(this.Accountservice.form.value).subscribe(() => {
        this.getAll(); 
        this.notificationagencyService.success(':: Submitted successfully');
      })
      this.Accountservice.form.reset();
      this.Accountservice.initializeFormGroup();
      this.onClose();
    }
    this.reloadPage();
    this.getAll();
  }


  reloadPage() {
    setTimeout(()=>{
        window.location.reload();
      }, 1000);  
  }

  onClose() {
    this.Accountservice.form.reset();
    this.Accountservice.initializeFormGroup();
    this.dialogRef.close();
  }
  alertConfirmation() {
    Swal.fire({
      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    }).then((result) => {
      if (result.value) {
        Swal.fire('Updated!', 'Company updated successfully.', 'success');
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        Swal.fire('Cancelled');
      }
    });
  }
}
