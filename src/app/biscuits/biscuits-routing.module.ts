import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BiscuitsComponent } from './biscuits.component';

const routes: Routes = [{ path: '', component: BiscuitsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BiscuitsRoutingModule { }
