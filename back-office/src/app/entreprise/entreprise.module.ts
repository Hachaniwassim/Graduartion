import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EntrepriseRoutingModule } from './entreprise-routing.module';
import { EntrepriseComponent } from './entreprise.component';
import { EntrepriseListComponent } from './entreprise-list/entreprise-list.component';
import { EntrepriseAddComponent } from './entreprise-add/entreprise-add.component';
import { EntrepriseViewComponent } from './entreprise-view/entreprise-view.component';


@NgModule({
  declarations: [
    EntrepriseComponent,
    EntrepriseListComponent,
    EntrepriseAddComponent,
    EntrepriseViewComponent
  ],
  imports: [
    CommonModule,
    EntrepriseRoutingModule
  ]
})
export class EntrepriseModule { }
