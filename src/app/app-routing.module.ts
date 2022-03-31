import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from "@angular/forms";
import { CommonModule } from '@angular/common';
import { MainTemplateComponent } from "./main-template/main-template.component";
import { HomeComponent } from "./home/home.component";
import { NotfoundComponent } from "./notfound/notfound.component";



const routes: Routes = [
{ path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
{ path: 'users', loadChildren: () => import('./users/users.module').then(m => m.UsersModule) },
{ path: '', redirectTo: '/home', pathMatch: 'full' },
{ path: 'configurations', loadChildren: () => import('./configurations/configurations.module').then(m => m.ConfigurationsModule) },
{ path: 'gestion', loadChildren: () => import('./gestion/gestion.module').then(m => m.GestionModule) },
{ path: 'qui-sommes-nous', loadChildren: () => import('./qui-sommes-nous/qui-sommes-nous.module').then(m => m.QuiSommesNousModule) },
{ path: 'support', loadChildren: () => import('./support/support.module').then(m => m.SupportModule) },
{ path: 'liens-utiles', loadChildren: () => import('./liens-utiles/liens-utiles.module').then(m => m.LiensUtilesModule) },
{ path: 'base-de-donnes', loadChildren: () => import('./base-de-donnes/base-de-donnes.module').then(m => m.BaseDeDonnesModule) },
{ path: 'biscuits', loadChildren: () => import('./biscuits/biscuits.module').then(m => m.BiscuitsModule) },
{ path: 'confidentialite', loadChildren: () => import('./confidentialite/confidentialite.module').then(m => m.ConfidentialiteModule) },
{ path: '**', component: NotfoundComponent },



];

@NgModule({
  declarations: [
  ],
  imports: [RouterModule.forRoot(routes),FormsModule,CommonModule],
  exports: [RouterModule],


})
export class AppRoutingModule { }