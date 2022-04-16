import { NgModule  } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersComponent } from './users.component';
import { MaterialModule } from '../material/material.module';
import {MatButtonModule} from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';


const routes: Routes = [{ path: '', component: UsersComponent }];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
    MaterialModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    CommonModule
  ],
  exports: [
    RouterModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],

})
export class UsersRoutingModule { }
