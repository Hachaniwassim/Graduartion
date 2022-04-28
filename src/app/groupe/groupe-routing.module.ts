import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { GroupeComponent } from './groupe.component';

const routes: Routes = [{ path: '', component: GroupeComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule],
  exports: [RouterModule,MaterialModule]
})
export class GroupeRoutingModule { }
