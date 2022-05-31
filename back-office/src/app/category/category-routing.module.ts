import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { CKEditorModule } from 'ckeditor4-angular';
import { MaterialModule } from '../material/material.module';
import { CategoryComponent } from './category.component';

const routes: Routes = [{ path: '', component: CategoryComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes), MaterialModule,ReactiveFormsModule,FormsModule,CKEditorModule],
  exports: [RouterModule, MaterialModule, ReactiveFormsModule,FormsModule,CKEditorModule]
})
export class CategoryRoutingModule { }
