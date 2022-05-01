import { CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GroupeRoutingModule } from './groupe-routing.module';
import { GroupeComponent } from './groupe.component';
import { GroupeListComponent } from './groupe-list/groupe-list.component';
import { GroupeAddComponent } from './groupe-add/groupe-add.component';
import { GroupeViewComponent } from './groupe-view/groupe-view.component';
import { NgxSpinnerModule } from 'ngx-spinner';


@NgModule({
  declarations: [
    GroupeComponent,
    GroupeListComponent,
    GroupeAddComponent,
    GroupeViewComponent
  ],
  imports: [
    CommonModule,
    GroupeRoutingModule,
    NgxSpinnerModule
  ],schemas: [CUSTOM_ELEMENTS_SCHEMA,NO_ERRORS_SCHEMA],
})
export class GroupeModule { }
