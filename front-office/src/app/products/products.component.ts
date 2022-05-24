import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/_services/products.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
   // public teamsList: Teams[] = teams;
 //public productslist: any = [];
 //constructor( private productservice : ProductService) { }

// ngOnInit(): void {
  // this.productservice.getAllproductsByEntreprise().subscribe(
   //  res => this.productslist = res
  // );
 //}

}
