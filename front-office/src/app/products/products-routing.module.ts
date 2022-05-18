import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from './products.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { MaterialModule } from '../material/material.module';

const routes: Routes = [{ path: '', component: ProductsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes) , MaterialModule],
  exports: [RouterModule,    MaterialModule  ],
  declarations: [
    ProductDetailsComponent,
  ]
})
export class ProductsRoutingModule { }
