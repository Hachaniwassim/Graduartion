import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { productsDTO } from 'src/app/models/dto/productsDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { ProductService } from 'src/app/_services/products.service';


@Component({
  selector: 'app-posproductlist-add',
  templateUrl: './posproductlist-add.component.html',
  styleUrls: ['./posproductlist-add.component.css']
})
export class PosproductlistAddComponent implements OnInit {
  @ViewChild('companyForm', { static: false })
  productForm!: FormGroup;
  productData !: productsDTO;
  product!: productsDTO[];
  searchKey!: string;
  showspinner = false;
  datasource = new MatTableDataSource(this.product)
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort, {}) sort!: MatSort;



  constructor(private dialog: MatDialog, private dialogService: DialogService, public productservice: ProductService, public dialogRef: MatDialogRef<PosproductlistAddComponent>,
    private notificationService: NotificationService, private router: Router, public _location: Location) {
    this.productData = {} as productsDTO;
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
    this.productservice.getAllCompanyBussiness().subscribe((response: any) => {
      this.datasource.data = response;
    })
  }

  //clear data

  onClear() {
    this.productservice.form.reset();
    this.productservice.initializeFormGroup();
  }

  // submit data with context EDITE : CREATE
  onSubmit() {
    if (this.productservice.form.valid) {
      if (!this.productservice.form.get('id')?.value)
        this.productservice.createCompayBusiness(this.productservice.form.value).subscribe(() => {
        })

      else(
        this.productservice.updateCompanyBusiness(this.productservice.form.value).subscribe(() => {
        }))
      this.productservice.form.reset();
      this.productservice.initializeFormGroup();
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
    this.productservice.form.reset();
    this.productservice.initializeFormGroup();
    this.dialogRef.close();
  }

}
