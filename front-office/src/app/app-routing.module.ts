import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NotfoundComponent } from "./notfound/notfound.component";

const routes : Routes =[

  { path : "home", component : HomeComponent},

  { path: 'products', loadChildren: () => import('./products/products.module').then(m => m.ProductsModule) },

  { path: 'sellers', loadChildren: () => import('./sellers/sellers.module').then(m => m.SellersModule) },

  // 404 not found
  { path: '**', component: NotfoundComponent },

]
  

@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports :[RouterModule]
})
export class AppRoutingModule { }
