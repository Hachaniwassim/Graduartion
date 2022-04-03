import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConfigurationsComponent } from './configurations.component';
import { MaterialModule } from '../material/material.module';
const routes: Routes = [{ path: '', component: ConfigurationsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule],
  exports: [RouterModule,MaterialModule]
})
export class ConfigurationsRoutingModule { }
