import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ConfidentialiteRoutingModule } from './confidentialite-routing.module';
import { ConfidentialiteComponent } from './confidentialite.component';


@NgModule({
  declarations: [
    ConfidentialiteComponent
  ],
  imports: [
    CommonModule,
    ConfidentialiteRoutingModule
  ]
})
export class ConfidentialiteModule { }
