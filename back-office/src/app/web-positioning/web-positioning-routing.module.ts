import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WebPositioningComponent } from './web-positioning.component';

const routes: Routes = [{ path: '', component: WebPositioningComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WebPositioningRoutingModule { }
