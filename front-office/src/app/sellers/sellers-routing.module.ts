import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SellersComponent } from './sellers.component';
import { SellerDetailsComponent } from './seller-details/seller-details.component';

const routes: Routes = [{ path: '', component: SellersComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  declarations: [
    SellerDetailsComponent
  ]
})
export class SellersRoutingModule { }
