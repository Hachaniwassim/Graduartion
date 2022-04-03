import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GestionComponent } from './gestion.component';
import { MaterialModule } from '../material/material.module';
const routes: Routes = [{ path: '', component: GestionComponent }];


@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule],
  exports: [RouterModule,MaterialModule]
})
export class GestionRoutingModule { }
