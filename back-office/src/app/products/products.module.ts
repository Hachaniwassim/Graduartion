import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductsRoutingModule } from './products-routing.module';
import { ProductsComponent } from './products.component';
import { MaterialModule } from '../material/material.module';
import { AddProductComponent } from './add-product/add-product.component';


@NgModule({
  declarations: [
    ProductsComponent,
    AddProductComponent
  ],
  imports: [
    CommonModule,
    ProductsRoutingModule,
  ]
  , exports:[
    MaterialModule]
})
export class ProductsModule { }
