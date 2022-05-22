import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
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
    private notificationService: NotificationService, private router: Router, public _location: Location) {
    this.companyData = {} as CompanyBusinessDTO;
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
    this.companyService.getAllCompanyBussiness().subscribe((response: any) => {
      this.datasource.data = response;
    })
  }

  //clear data

  onClear() {
    this.companyService.form.reset();
    this.companyService.initializeFormGroup();
  }

  // submit data with context EDITE : CREATE
  onSubmit() {
    if (this.companyService.form.valid) {
      if (!this.companyService.form.get('id')?.value)
        this.companyService.createCompayBusiness(this.companyService.form.value).subscribe((response) => {
          this.company.push(response);
        })

      else(
        this.companyService.updateCompanyBusiness(this.companyService.form.value).subscribe((response) => {
          this.company.push(response);
        }))
      this.companyService.form.reset();
      this.companyService.initializeFormGroup();
      this.onClose();
  

    }
    
    this.companyService.form.reset();
    this.companyService.initializeFormGroup();
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
    this.companyService.form.reset();
    this.companyService.initializeFormGroup();
    this.dialogRef.close();
  }

}
