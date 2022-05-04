import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LanguageRoutingModule } from './language-routing.module';
import { LanguageComponent } from './language.component';
import { LanguageListComponent } from './language-list/language-list.component';
import { AddLanguageComponent } from './add-language/add-language.component';


@NgModule({
  declarations: [
    LanguageComponent,
    LanguageListComponent,
    AddLanguageComponent
  ],
  imports: [
    CommonModule,
    LanguageRoutingModule
  ]
})
export class LanguageModule { }
