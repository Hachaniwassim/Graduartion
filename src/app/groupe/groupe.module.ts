import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GroupeRoutingModule } from './groupe-routing.module';
import { GroupeComponent } from './groupe.component';
import { GroupeListComponent } from './groupe-list/groupe-list.component';
import { GroupeAddComponent } from './groupe-add/groupe-add.component';
import { GroupeViewComponent } from './groupe-view/groupe-view.component';


@NgModule({
  declarations: [
    GroupeComponent,
    GroupeListComponent,
    GroupeAddComponent,
    GroupeViewComponent
  ],
  imports: [
    CommonModule,
    GroupeRoutingModule
  ]
})
export class GroupeModule { }
