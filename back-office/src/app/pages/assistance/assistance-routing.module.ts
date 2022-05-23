import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { CKEditorModule } from 'ckeditor4-angular';
import { MaterialModule } from 'src/app/material/material.module';
import { AssistanceComponent } from './assistance.component';

const routes: Routes = [{ path: '', component: AssistanceComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),
    CKEditorModule,
    FormsModule, 
    ReactiveFormsModule,
    MaterialModule
  ],
  exports: [
    RouterModule,
    CKEditorModule,
    FormsModule, 
    MaterialModule,
    ReactiveFormsModule]
})
export class AssistanceRoutingModule { }
