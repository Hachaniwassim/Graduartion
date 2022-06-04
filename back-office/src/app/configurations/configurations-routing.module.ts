import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConfigurationsComponent } from './configurations.component';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CKEditorModule } from 'ckeditor4-angular';
const routes: Routes = [{ path: '', component: ConfigurationsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule,CKEditorModule,ReactiveFormsModule,FormsModule],
  exports: [RouterModule,MaterialModule,ReactiveFormsModule,FormsModule
  ,CKEditorModule]
})
export class ConfigurationsRoutingModule { }
