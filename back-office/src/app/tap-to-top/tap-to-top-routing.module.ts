import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TapToTopComponent } from './tap-to-top.component';

const routes: Routes = [{ path: '', component: TapToTopComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TapToTopRoutingModule { }
