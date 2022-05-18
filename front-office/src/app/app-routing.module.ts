import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NotfoundComponent } from "./notfound/notfound.component";
import { MaterialModule } from './material/material.module';
import { FormsModule } from "@angular/forms";
import { LoginComponent } from "./login/login.component";
import { ResetpasswordComponent } from "./resetpassword/resetpassword.component";
import { RegisterComponent } from "./register/register.component";


const routes : Routes =[

  { path: 'login', component: LoginComponent },
  { path: 'resetpassword', component: ResetpasswordComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  //home

  { path : "home", component : HomeComponent},

  { path: 'products', loadChildren: () => import('./products/products.module').then(m => m.ProductsModule) },

  { path: 'sellers', loadChildren: () => import('./sellers/sellers.module').then(m => m.SellersModule) },

  // 404 not found
  { path: '**', component: NotfoundComponent },

]
  

@NgModule({
  declarations: [
    NotfoundComponent
  ],
  imports: [
    MaterialModule,
    RouterModule.forRoot(routes),
    FormsModule,
    CommonModule
  ],
  exports :[RouterModule]
})
export class AppRoutingModule { }
