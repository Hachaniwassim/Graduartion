import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { EntrepriseComponent } from './entreprise.component';
import {Ng2TelInputModule} from 'ng2-tel-input';

const routes: Routes = [{ path: '', component: EntrepriseComponent }];

@NgModule({
  imports: [
    
    RouterModule.forChild(routes),
    MaterialModule,
    FormsModule, 
    ReactiveFormsModule,
    Ng2TelInputModule
  ],
  exports:
   [
     RouterModule,
     MaterialModule,
     FormsModule,
     ReactiveFormsModule,
     Ng2TelInputModule]
})
export class EntrepriseRoutingModule { }
