import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { CompanyBusinessDTO } from 'src/app/models/dto/companyBusinessDTO';
import { NotificationService } from 'src/app/shared/notification.service';
import { CompanybusinessService } from 'src/app/_services/companybusiness.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-company-add',
  templateUrl: './company-add.component.html',
  styleUrls: ['./company-add.component.css']
})
export class CompanyAddComponent implements OnInit {

 company !: CompanyBusinessDTO[];
  datasource = new MatTableDataSource(this.company);
  constructor( public companyService : CompanybusinessService  ,private notificationagencyService : NotificationService,public dialogRef: MatDialogRef<CompanyAddComponent>) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.companyService.all().subscribe((response: any) => {
      this.datasource.data = response;
    });

  }
  
  onClear() {
    this.companyService.form.reset();
    this.companyService.initializeFormGroup();
  }

  onSubmit() {
    if (this.companyService.form.valid) {
      if ( ! this.companyService.form.get('id')?.value)
        this.companyService.create(this.companyService.form.value).subscribe(() => {
          this.notificationagencyService.success(':: Submitted successfully');
          this.ngOnInit();
        })
      else
      this.companyService.update(this.companyService.form.value).subscribe(() => {
        this.ngOnInit();
        this.notificationagencyService.success(':: Submitted successfully');
        
      })
      this.companyService.form.reset();
      this.companyService.initializeFormGroup();
      this.onClose();
      this.ngOnInit();
    }
    
    
  }

  
  reloadPage() {
    setTimeout(()=>{
        window.location.reload();
      }, 1000);  
  }

  onClose() {
    this.companyService.form.reset();
    this.companyService.initializeFormGroup();
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
