import { LanguageComponent } from './language.component';

import { CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { NgxSpinnerModule } from "ngx-spinner";

const routes: Routes = [{ path: '', component: LanguageComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),
    MaterialModule,
    FormsModule,
    NgxSpinnerModule,
   ReactiveFormsModule],
  exports: [RouterModule,MaterialModule,FormsModule, ReactiveFormsModule]
})
export class LanguageRoutingModule { }
