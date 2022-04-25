import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home.component';
import { MaterialModule } from '../material/material.module';
import { ScheduleModule } from '@syncfusion/ej2-angular-schedule';
const routes: Routes = [{ path: '', component: HomeComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule,ScheduleModule],
  exports: [RouterModule,MaterialModule,ScheduleModule]
})
export class HomeRoutingModule { }
