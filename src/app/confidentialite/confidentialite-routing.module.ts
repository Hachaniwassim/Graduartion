import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConfidentialiteComponent } from './confidentialite.component';

const routes: Routes = [{ path: '', component: ConfidentialiteComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ConfidentialiteRoutingModule { }
