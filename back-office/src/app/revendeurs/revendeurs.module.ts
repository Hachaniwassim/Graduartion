import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RevendeursRoutingModule } from './revendeurs-routing.module';
import { RevendeursComponent } from './revendeurs.component';


@NgModule({
  declarations: [
    RevendeursComponent
  ],
  imports: [
    CommonModule,
    RevendeursRoutingModule
  ]
})
export class RevendeursModule { }
