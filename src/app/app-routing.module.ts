import { NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from "@angular/forms";
import { CommonModule } from '@angular/common';
import { MainTemplateComponent } from "./main-template/main-template.component";
import { HomeComponent } from "./home/home.component";
import { NotfoundComponent } from "./notfound/notfound.component";
import { MaterialModule } from './material/material.module';
import { DatabaseComponent } from './database/database.component';
import { WhoareweComponent } from './whoarewe/whoarewe.component';
import { CookiesComponent } from './cookies/cookies.component';
import { PrivacypolicyComponent } from './privacypolicy/privacypolicy.component';
import { ManagementComponent } from './management/management.component';
import { UsefulllinksComponent } from './usefulllinks/usefulllinks.component';

//Positioning Web
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
import { PoshomeComponent } from './poshome/poshome.component';
import { PosprivacyComponent } from './posprivacy/posprivacy.component';
//Home
import { HomewelcometextComponent } from './homewelcometext/homewelcometext.component';
import { HomenewslistComponent } from './homenewslist/homenewslist.component';
import { HomelistComponent } from './homelist/homelist.component';
import { HomescustomerlogosComponent } from './homescustomerlogos/homescustomerlogos.component';
import { HomeprimaryslideComponent } from './homeprimaryslide/homeprimaryslide.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';







const routes: Routes = [
  
{ path: 'login', component:LoginComponent},
{ path : 'register', component: RegisterComponent},
{ path: '', redirectTo: '/login', pathMatch: 'full' },

{ path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
{ path: 'users', loadChildren: () => import('./users/users.module').then(m => m.UsersModule) },

{ path: 'configurations', loadChildren: () => import('./configurations/configurations.module').then(m => m.ConfigurationsModule) },
{ path: 'support', loadChildren: () => import('./support/support.module').then(m => m.SupportModule) },
{ path: 'liens-utiles', loadChildren: () => import('./liens-utiles/liens-utiles.module').then(m => m.LiensUtilesModule) },
{ path: 'biscuits', loadChildren: () => import('./biscuits/biscuits.module').then(m => m.BiscuitsModule) },
{ path: 'confidentialite', loadChildren: () => import('./confidentialite/confidentialite.module').then(m => m.ConfidentialiteModule) },
//Positioning Web
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
{ path : "poshome" ,component :PoshomeComponent},
{ path : "posprivacy" ,component :PosprivacyComponent},
//Home
{ path : "homewelcometext" ,component :HomewelcometextComponent},
{ path : "homenewslist" ,component :HomenewslistComponent},
{ path : "homecustomerlogos" ,component :HomescustomerlogosComponent},
{ path : "homelist" ,component :HomelistComponent},
{ path : "homeprimaryslide" ,component :HomeprimaryslideComponent},
//
{ path : "database" ,component :DatabaseComponent},
{ path : "whoarewe" ,component :WhoareweComponent},
{ path : "privacypolicy" ,component :PrivacypolicyComponent},
{ path : "cookies" ,component :CookiesComponent},
{ path : "management" ,component :ManagementComponent},
{ path : "usefullinks" ,component :UsefulllinksComponent},


{ path: 'user', component: BoardUserComponent },
{ path: 'mod', component: BoardModeratorComponent },
{ path: 'admin', component: BoardAdminComponent },
{ path: 'profile', component: ProfileComponent },

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
  exports: [RouterModule,MaterialModule],


})
export class AppRoutingModule { }