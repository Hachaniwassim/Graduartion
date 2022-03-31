import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QuiSommesNousRoutingModule } from './qui-sommes-nous-routing.module';
import { QuiSommesNousComponent } from './qui-sommes-nous.component';


@NgModule({
  declarations: [
    QuiSommesNousComponent
  ],
  imports: [
    CommonModule,
    QuiSommesNousRoutingModule
  ]
})
export class QuiSommesNousModule { }
