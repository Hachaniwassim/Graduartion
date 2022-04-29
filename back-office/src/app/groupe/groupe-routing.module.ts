import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { GroupeComponent } from './groupe.component';

const routes: Routes = [{ path: '', component: GroupeComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule,FormsModule, 
    ReactiveFormsModule],
  exports: [RouterModule,MaterialModule,FormsModule, ReactiveFormsModule]
})
export class GroupeRoutingModule { }
