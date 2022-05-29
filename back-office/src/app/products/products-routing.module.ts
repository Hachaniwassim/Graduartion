import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { ProductsComponent } from './products.component';

const routes: Routes = [{ path: '', component: ProductsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule,ReactiveFormsModule,FormsModule],
  exports: [RouterModule, MaterialModule,ReactiveFormsModule,FormsModule]
})
export class ProductsRoutingModule { }
