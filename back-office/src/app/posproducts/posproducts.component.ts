import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { productsDTO } from 'src/app/models/dto/productsDTO';
import { DialogService } from 'src/app/shared/dialog.service';
import { NotificationService } from 'src/app/shared/notification.service';
import { ProductService } from 'src/app/_services/products.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-posproducts',
  templateUrl: './posproducts.component.html',
  styleUrls: ['./posproducts.component.css']
})
export class PosproductsComponent implements OnInit {
  public productlist: any = [];
  constructor( private teamsService : ProductService) { }

  ngOnInit(): void {
    this.teamsService.getAllCompanyBussiness().subscribe(
      res => this.productlist = res
    );
  }

}
