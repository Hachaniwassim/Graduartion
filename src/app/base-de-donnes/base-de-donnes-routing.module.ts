import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseDeDonnesComponent } from './base-de-donnes.component';
import { MaterialModule } from '../material/material.module';
const routes: Routes = [{ path: '', component: BaseDeDonnesComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule],
  exports: [RouterModule,MaterialModule]
})
export class BaseDeDonnesRoutingModule { }
