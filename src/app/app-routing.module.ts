import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from "@angular/forms";
import { CommonModule } from '@angular/common';
import { MainTemplateComponent } from "./main-template/main-template.component";
import { HomeComponent } from "./home/home.component";


const routes: Routes = [{ path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
{ path: 'users', loadChildren: () => import('./users/users.module').then(m => m.UsersModule) },

];

@NgModule({
  declarations: [
  ],
  imports: [RouterModule.forRoot(routes),FormsModule,CommonModule],
  exports: [RouterModule],


})
export class AppRoutingModule { }