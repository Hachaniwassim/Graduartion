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
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {
  public productlist: any = [];
  constructor(private dialog: MatDialog, private dialogService: DialogService, public productService: ProductService, public dialogRef: MatDialogRef<ProductAddComponent>,
    private notificationService: NotificationService, private router: Router, public _location: Location) {
  }

  onClear() {
    this.productService.form.reset();
    this.productService.initializeFormGroup();
  }
    // dialogue close 
    onClose() {
      this.productService.form.reset();
      this.productService.initializeFormGroup();
      this.dialogRef.close();
    }
  
    //refrech 
    refresh(): void {
      this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
        console.log(decodeURI(this._location.path()));
        this.router.navigate([decodeURI(this._location.path())]);
      });
    }
  onSubmit() {
    if (this.productService.form.valid) {
      if (!this.productService.form.get('id')?.value)
        this.productService.createproduct(this.productService.form.value).subscribe(() => {
        })

      else(
        this.productService.update(this.productService.form.value).subscribe(() => {
        }))
      this.productService.form.reset();
      this.productService.initializeFormGroup();
      this.onClose();

    }
    this.refresh();

  }
  ngOnInit(): void {
    // this.productService.getAllProductsByEntreprise().subscribe(
    //   (res :any)=> this.productlist = res
    // );
  }



}
