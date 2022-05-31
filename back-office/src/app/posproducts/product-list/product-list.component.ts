import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { DialogService } from 'src/app/shared/dialog.service';
import { ProductService } from 'src/app/_services/products.service';
import Swal from 'sweetalert2';
import { productsDTO } from 'src/app/models/dto/productsDTO';
import { NotificationService } from 'src/app/shared/notification.service';
import { ProductAddComponent } from '../product-add/product-add.component';
@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  public productlist: any = [];
  constructor( private productService : ProductService , private dialog: MatDialog, private dialogService: DialogService,private notificationService: NotificationService,private route: ActivatedRoute, public router: Router, public _location: Location ) { }

  onCreateproduct() {
    //this.companyService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(ProductAddComponent, dialogConfig);

  }
  

  onEditproduct(product: any) {
    this.productService.populateForm(product);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(ProductAddComponent, dialogConfig);
  }
    // delete data 
    onDeleteproduct(id: number) {

      this.dialogService.openConfirmDialog('Are you sure to delete this record ?')
        .afterClosed().subscribe((res: any) => {
          if (res) {
            this.productService.deleterPoducts(id).subscribe(() => {
              //filter sur l objet  optionnel
  
             /*this.datasource.data = this.datasource.data.filter((o: any) => {
                return o.id !== id ? o : false;
              })
              console.log(this.datasource.data);*/
            })
            this.notificationService.success(' :: Deleted Successfully')
            this.refresh();
          }
        });
    }
    refresh(): void {
      this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
        console.log(decodeURI(this._location.path()));
        this.router.navigate([decodeURI(this._location.path())]);
      });
    }
  
    // clear data 
    onClear() {
      this.productService.form.reset();
      this.productService.initializeFormGroup();
    }
      //alerte de confirmation 
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
  
  ngOnInit(): void {
    this.productService.getAllProductsByEntreprise().subscribe(
      (res:any)=> {this.productlist = res}
    );
  }

}
