import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LiensUtilesRoutingModule } from './liens-utiles-routing.module';
import { LiensUtilesComponent } from './liens-utiles.component';


@NgModule({
  declarations: [
    LiensUtilesComponent
  ],
  imports: [
    CommonModule,
    LiensUtilesRoutingModule
  ]
})
export class LiensUtilesModule { }
