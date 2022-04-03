import { NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from "@angular/forms";
import { CommonModule } from '@angular/common';
import { MainTemplateComponent } from "./main-template/main-template.component";
import { HomeComponent } from "./home/home.component";
import { NotfoundComponent } from "./notfound/notfound.component";
import { MaterialModule } from './material/material.module';
import { PosactualityComponent } from './posactuality/posactuality.component';
import { PoscontactsComponent } from './poscontacts/poscontacts.component';
import { PoscookiesComponent } from './poscookies/poscookies.component';
import { PosdatabaseComponent } from './posdatabase/posdatabase.component';
import { PosdealersComponent } from './posdealers/posdealers.component';
import { PosmanagementComponent } from './posmanagement/posmanagement.component';
import { PosnewsComponent } from './posnews/posnews.component';
import { PosproductlistComponent } from './posproductlist/posproductlist.component';
import { PosproductsComponent } from './posproducts/posproducts.component';
import { PossupportComponent } from './possupport/possupport.component';
import { PosusefulllinksComponent } from './posusefulllinks/posusefulllinks.component';
import { PoswhorareweComponent } from './poswhorarewe/poswhorarewe.component';






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
{ path : "posactuality" ,component :PosactualityComponent},
{ path : "Poscontacts" ,component :PoscontactsComponent},
{ path : "poscookies" ,component :PoscookiesComponent},
{ path : "posdatabase" ,component :PosdatabaseComponent},
{ path : "posdealers" ,component :PosdealersComponent},
{ path : "posmanagement" ,component :PosmanagementComponent},
{ path : "posnews" ,component :PosnewsComponent},
{ path : "posproductlist" ,component :PosproductlistComponent},
{ path : "posproducts" ,component :PosproductsComponent},
{ path : "possupport" ,component :PossupportComponent},
{ path : "posusefulllinks" ,component :PosusefulllinksComponent},
{ path : "poswhoarewe" ,component :PoswhorareweComponent},



{ path: '**', component: NotfoundComponent },



];

@NgModule({
  declarations: [
  ],
  imports: [
    MaterialModule,
    RouterModule.forRoot(routes),
    FormsModule,
    CommonModule
  ],
  exports: [RouterModule],


})
export class AppRoutingModule { }