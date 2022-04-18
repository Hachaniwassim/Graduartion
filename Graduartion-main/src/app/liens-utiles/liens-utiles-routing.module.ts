import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LiensUtilesComponent } from './liens-utiles.component';

const routes: Routes = [{ path: '', component: LiensUtilesComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LiensUtilesRoutingModule { }
