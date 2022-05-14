import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TapToTopRoutingModule } from './tap-to-top-routing.module';
import { TapToTopComponent } from './tap-to-top.component';


@NgModule({
  declarations: [
    TapToTopComponent
  ],
  imports: [
    CommonModule,
    TapToTopRoutingModule
  ]
})
export class TapToTopModule { }
