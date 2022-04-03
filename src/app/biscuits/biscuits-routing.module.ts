import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BiscuitsComponent } from './biscuits.component';
import { MaterialModule } from '../material/material.module';
const routes: Routes = [{ path: '', component: BiscuitsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule],
  exports: [RouterModule,MaterialModule]
})
export class BiscuitsRoutingModule { }
