import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WebPositioningRoutingModule } from './web-positioning-routing.module';
import { WebPositioningComponent } from './web-positioning.component';
import { NgSelectModule } from '@ng-select/ng-select';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatTabsModule } from '@angular/material/tabs';


@NgModule({
  declarations: [
    WebPositioningComponent
  ],
  imports: [
    CommonModule,
    WebPositioningRoutingModule,
    NgSelectModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatTabsModule,
    MatDividerModule,
  ]
})
export class WebPositioningModule { }
