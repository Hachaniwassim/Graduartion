import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { CKEditorModule } from 'ckeditor4-angular';
import { MaterialModule } from '../material/material.module';
import { RevendeursComponent } from './revendeurs.component';

const routes: Routes = [{ path: '', component: RevendeursComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes), MaterialModule, ReactiveFormsModule,FormsModule,CKEditorModule],
  exports: [RouterModule, MaterialModule, ReactiveFormsModule,FormsModule,CKEditorModule]
})
export class RevendeursRoutingModule { }
