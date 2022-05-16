import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home.component';
import { ScheduleModule } from '@syncfusion/ej2-angular-schedule';
import { MaterialModule } from 'src/app/material/material.module';
const routes: Routes = [{ path: '', component: HomeComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule,ScheduleModule],
  exports: [RouterModule,MaterialModule,ScheduleModule]
})
export class HomeRoutingModule { }
