import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BaseDeDonnesComponent } from './base-de-donnes.component';

const routes: Routes = [{ path: '', component: BaseDeDonnesComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BaseDeDonnesRoutingModule { }
