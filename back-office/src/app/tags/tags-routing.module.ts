import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from '../material/material.module';
import { TagsComponent } from './tags.component';

const routes: Routes = [{ path: '', component: TagsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes), MaterialModule,ReactiveFormsModule,FormsModule],
  exports: [RouterModule,MaterialModule,ReactiveFormsModule,FormsModule]
})
export class TagsRoutingModule { }
