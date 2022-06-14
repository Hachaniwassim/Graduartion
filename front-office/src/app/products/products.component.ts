import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/_services/products.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  
 public productslist: any = [];
 constructor( private productservice : ProductService) { }

ngOnInit(): void {

  this.getProduct();

 }


 getProduct(){
  this.productservice.getAllproductsByEntreprise().subscribe(
    ( res : any) => this.productslist = res
  );
 }
 reloadPage() {
   window.location.href='/'
 }
}
