import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CompanyBusinessDTO } from 'src/app/models/dto/companyBusinessDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { CompanybusinessService } from 'src/app/_services/companybusiness.service';


@Component({
  selector: 'app-company-add',
  templateUrl: './company-add.component.html',
  styleUrls: ['./company-add.component.css']
})

export class CompanyAddComponent implements OnInit {

  @ViewChild('companyForm', { static: false })
  companyForm!: FormGroup;
  companyData !: CompanyBusinessDTO;
  company!: CompanyBusinessDTO[];
  searchKey!: string;
  showspinner = false;
  datasource = new MatTableDataSource(this.company)
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;

  constructor(private dialog: MatDialog, private dialogService: DialogService, public companyService: CompanybusinessService, public dialogRef: MatDialogRef<CompanyAddComponent>,
    private notificationService: NotificationService) {
    this.companyData = {} as CompanyBusinessDTO;
  }


  ngAfterViewInit() {
    this.datasource.paginator = this.paginator;
    this.datasource.sort = this.sort;
  }

  ngOnInit(): void {
    this.datasource.sort = this.sort;
    this.datasource.paginator = this.paginator;
    this.companyService.all().subscribe((response: any) => {
      this.datasource.data = response;
  })
}

  

  onClear() {
    this.companyService.form.reset();
    this.companyService.initializeFormGroup();
  }

  onSubmit() {
    if (this.companyService.form.valid) {
      if (!this.companyService.form.get('id')?.value)
        this.companyService.create(this.companyService.form.value).subscribe(() => {
          this.ngOnInit();
        })

      else
        this.companyService.update(this.companyService.form.value).subscribe(() => {
        })
        this.ngOnInit();
        /*this.companyService.form.reset();
        this.companyService.initializeFormGroup();*/
       this.onClose();

    }
    this.ngOnInit();

  }


  reloadPage() {
    setTimeout(() => {
      window.location.reload();
    }, 1000);
  }

  onClose() {
    this.companyService.form.reset();
    this.companyService.initializeFormGroup();
    this.dialogRef.close();
  }
 
}
