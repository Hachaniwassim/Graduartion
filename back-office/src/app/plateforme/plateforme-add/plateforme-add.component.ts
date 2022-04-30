import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { plateforomeDTO } from 'src/app/models/dto/plateformeDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { PlateformeService } from 'src/app/_services/plateforme.service';


@Component({
  selector: 'app-plateforme-add',
  templateUrl: './plateforme-add.component.html',
  styleUrls: ['./plateforme-add.component.css']
})
export class PlateformeAddComponent implements OnInit {
  @ViewChild('companyForm', { static: false })
  companyForm!: FormGroup;
  companyData !: plateforomeDTO;
  company!: plateforomeDTO[];
  searchKey!: string;
  showspinner = false;
  datasource = new MatTableDataSource(this.company)
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;



  constructor(private dialog: MatDialog, private dialogService: DialogService, public plateformeservice: PlateformeService, public dialogRef: MatDialogRef<PlateformeAddComponent>,
    private notificationService: NotificationService, private router: Router, public _location: Location) {
    this.companyData = {} as plateforomeDTO;
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
    this.plateformeservice.getAllplateformes().subscribe((response: any) => {
      this.datasource.data = response;
    })
  }

  //clear data

  onClear() {
    this.plateformeservice.form.reset();
    this.plateformeservice.initializeFormGroup();
  }

  // submit data with context EDITE : CREATE
  onSubmit() {
    if (this.plateformeservice.form.valid) {
      if (!this.plateformeservice.form.get('id')?.value)
        this.plateformeservice.createplateforme(this.plateformeservice.form.value).subscribe(() => {
        })

      else(
        this.plateformeservice.updateplateforme(this.plateformeservice.form.value).subscribe(() => {
        }))
      this.plateformeservice.form.reset();
      this.plateformeservice.initializeFormGroup();
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
    this.plateformeservice.form.reset();
    this.plateformeservice.initializeFormGroup();
    this.dialogRef.close();
  }

}
