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
import { AuthGuard } from './guard/auth.guard';







const routes: Routes = [
  
{ path: 'login', component:LoginComponent},
{ path : 'register', component: RegisterComponent},
{ path: '', redirectTo: '/login', pathMatch: 'full' },

{ path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) ,canActivate: [AuthGuard]},
{ path: 'users', loadChildren: () => import('./users/users.module').then(m => m.UsersModule),canActivate: [AuthGuard] },

{ path: 'configurations', loadChildren: () => import('./configurations/configurations.module').then(m => m.ConfigurationsModule),canActivate: [AuthGuard] },
{ path: 'support', loadChildren: () => import('./support/support.module').then(m => m.SupportModule) },
{ path: 'liens-utiles', loadChildren: () => import('./liens-utiles/liens-utiles.module').then(m => m.LiensUtilesModule),canActivate: [AuthGuard] },
{ path: 'biscuits', loadChildren: () => import('./biscuits/biscuits.module').then(m => m.BiscuitsModule) },
{ path: 'confidentialite', loadChildren: () => import('./confidentialite/confidentialite.module').then(m => m.ConfidentialiteModule),canActivate: [AuthGuard] },
//Positioning Web
{ path : "posactuality" ,component :PosactualityComponent,canActivate: [AuthGuard]},
{ path : "Poscontacts" ,component :PoscontactsComponent,canActivate: [AuthGuard]},
{ path : "poscookies" ,component :PoscookiesComponent,canActivate: [AuthGuard]},
{ path : "posdatabase" ,component :PosdatabaseComponent,canActivate: [AuthGuard]},
{ path : "posdealers" ,component :PosdealersComponent,canActivate: [AuthGuard]},
{ path : "posmanagement" ,component :PosmanagementComponent,canActivate: [AuthGuard]},
{ path : "posnews" ,component :PosnewsComponent,canActivate: [AuthGuard]},
{ path : "posproductlist" ,component :PosproductlistComponent,canActivate: [AuthGuard]},
{ path : "posproducts" ,component :PosproductsComponent,canActivate: [AuthGuard]},
{ path : "possupport" ,component :PossupportComponent,canActivate: [AuthGuard]},
{ path : "posusefulllinks" ,component :PosusefulllinksComponent,canActivate: [AuthGuard]},
{ path : "poswhoarewe" ,component :PoswhorareweComponent,canActivate: [AuthGuard]},
{ path : "poshome" ,component :PoshomeComponent,canActivate: [AuthGuard]},
{ path : "posprivacy" ,component :PosprivacyComponent,canActivate: [AuthGuard]},
//Home
{ path : "homewelcometext" ,component :HomewelcometextComponent,canActivate: [AuthGuard]},
{ path : "homenewslist" ,component :HomenewslistComponent,canActivate: [AuthGuard]},
{ path : "homecustomerlogos" ,component :HomescustomerlogosComponent,canActivate: [AuthGuard]},
{ path : "homelist" ,component :HomelistComponent,canActivate: [AuthGuard]},
{ path : "homeprimaryslide" ,component :HomeprimaryslideComponent,canActivate: [AuthGuard]},
//
{ path : "database" ,component :DatabaseComponent,canActivate: [AuthGuard]},
{ path : "whoarewe" ,component :WhoareweComponent,canActivate: [AuthGuard]},
{ path : "privacypolicy" ,component :PrivacypolicyComponent,canActivate: [AuthGuard]},
{ path : "cookies" ,component :CookiesComponent,canActivate: [AuthGuard]},
{ path : "management" ,component :ManagementComponent,canActivate: [AuthGuard]},
{ path : "usefullinks" ,component :UsefulllinksComponent,canActivate: [AuthGuard]},


{ path: 'user', component: BoardUserComponent,canActivate: [AuthGuard] },
{ path: 'mod', component: BoardModeratorComponent ,canActivate: [AuthGuard]},
{ path: 'admin', component: BoardAdminComponent,canActivate: [AuthGuard] },
{ path: 'profile', component: ProfileComponent,canActivate: [AuthGuard] },
  
{ path: 'dashboard', loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule),canActivate: [AuthGuard]},

{ path: '**', component: NotfoundComponent },



];

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
  exports: [RouterModule,MaterialModule],


})
export class AppRoutingModule { }