import { NgModule  } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersComponent } from './users.component';
import { MaterialModule } from '../material/material.module';
import {MatButtonModule} from '@angular/material/button';

const routes: Routes = [{ path: '', component: UsersComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule],
  exports: [RouterModule,MaterialModule],

})
export class UsersRoutingModule { }
