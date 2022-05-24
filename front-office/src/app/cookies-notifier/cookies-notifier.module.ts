import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CookiesNotifierRoutingModule } from './cookies-notifier-routing.module';
import { CookiesNotifierComponent } from './cookies-notifier.component';


@NgModule({
  declarations: [
    CookiesNotifierComponent
  ],
  imports: [
    CommonModule,
    CookiesNotifierRoutingModule
  ]
})
export class CookiesNotifierModule { }
