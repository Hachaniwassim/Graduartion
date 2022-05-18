import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SellersComponent } from './sellers.component';
import { SellerDetailsComponent } from './seller-details/seller-details.component';
import { MaterialModule } from '../material/material.module';

const routes: Routes = [{ path: '', component: SellersComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes),MaterialModule],
  exports: [RouterModule,MaterialModule],
  declarations: [
    SellerDetailsComponent
  ]
})
export class SellersRoutingModule { }
