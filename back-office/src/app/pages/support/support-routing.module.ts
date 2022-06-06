import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SupportComponent } from './support.component';
import { MaterialModule } from '../../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CKEditorModule } from 'ckeditor4-angular';

const routes: Routes = [{ path: '', component: SupportComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule, ReactiveFormsModule,FormsModule,CKEditorModule],
  exports: [RouterModule,MaterialModule,ReactiveFormsModule,FormsModule,CKEditorModule]
})
export class SupportRoutingModule { }
