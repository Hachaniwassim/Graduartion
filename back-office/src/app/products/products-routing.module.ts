import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { NgSelectModule } from '@ng-select/ng-select';
import { CKEditorModule } from 'ckeditor4-angular';
import { MaterialModule } from '../material/material.module';
import { ProductsComponent } from './products.component';

const routes: Routes = [{ path: '', component: ProductsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule,ReactiveFormsModule,FormsModule,CKEditorModule,NgSelectModule],
  exports: [RouterModule,MaterialModule,ReactiveFormsModule,FormsModule,CKEditorModule,NgSelectModule]
})
export class ProductsRoutingModule { }
