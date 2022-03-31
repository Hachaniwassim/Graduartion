import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BaseDeDonnesRoutingModule } from './base-de-donnes-routing.module';
import { BaseDeDonnesComponent } from './base-de-donnes.component';


@NgModule({
  declarations: [
    BaseDeDonnesComponent
  ],
  imports: [
    CommonModule,
    BaseDeDonnesRoutingModule
  ]
})
export class BaseDeDonnesModule { }
