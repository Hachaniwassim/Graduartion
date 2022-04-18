import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConfidentialiteComponent } from './confidentialite.component';
import { MaterialModule } from '../material/material.module';
const routes: Routes = [{ path: '', component: ConfidentialiteComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule],
  exports: [RouterModule,MaterialModule]
})
export class ConfidentialiteRoutingModule { }
