import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BiscuitsRoutingModule } from './biscuits-routing.module';
import { BiscuitsComponent } from './biscuits.component';


@NgModule({
  declarations: [
    BiscuitsComponent
  ],
  imports: [
    CommonModule,
    BiscuitsRoutingModule
  ]
})
export class BiscuitsModule { }
