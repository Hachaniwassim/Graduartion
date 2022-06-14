import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from "@angular/forms";
import { CommonModule } from '@angular/common';
import { NotfoundComponent } from "./notfound/notfound.component";
import { MaterialModule } from './material/material.module';
import { DatabaseComponent } from './database/database.component';
import { CookiesComponent } from './cookies/cookies.component';
import { PrivacypolicyComponent } from './privacypolicy/privacypolicy.component';
import { ManagementComponent } from './management/management.component';
import { UsefulllinksComponent } from './pages/usefulllinks/usefulllinks.component';

//Positioning Web
import { PosproductsComponent } from './posproducts/posproducts.component';
//Home
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from './guard/auth.guard';
import { CompanybusinessComponent } from './companybusiness/companybusiness.component';
import { AccountComponent } from './account/account.component';
import { CookiesV2Component } from './cookies-v2/cookies-v2.component';
import { PlateformeComponent } from './plateforme/plateforme.component';
import { PosactualityComponent } from './posWeb/posactuality/posactuality.component';
import { PoscontactsComponent } from './posWeb/poscontacts/poscontacts.component';
import { PoscookiesComponent } from './posWeb/poscookies/poscookies.component';
import { PosdatabaseComponent } from './posWeb/posdatabase/posdatabase.component';
import { PosdealersComponent } from './posWeb/posdealers/posdealers.component';
import { PosmanagementComponent } from './posWeb/posnews/posmanagement/posmanagement.component';
import { PosnewsComponent } from './posWeb/posnews/posnews.component';
import { PossupportComponent } from './posWeb/possupport/possupport.component';
import { PosusefulllinksComponent } from './posWeb/posusefulllinks/posusefulllinks.component';
import { PoswhorareweComponent } from './posWeb/poswhorarewe/poswhorarewe.component';
import { PoshomeComponent } from './posWeb/poshome/poshome.component';
import { PosprivacyComponent } from './posWeb/posprivacy/posprivacy.component';
import { HomewelcometextComponent } from './pages/homewelcometext/homewelcometext.component';
import { HomenewslistComponent } from './pages/homenewslist/homenewslist.component';
import { HomescustomerlogosComponent } from './pages/homescustomerlogos/homescustomerlogos.component';
import { HomelistComponent } from './pages/homelist/homelist.component';
import { HomeprimaryslideComponent } from './pages/homeprimaryslide/homeprimaryslide.component';
import { ResetpasswordComponent } from "./resetpassword/resetpassword.component";
import { WhoareweComponent } from './pages/whoarewe/whoarewe.component';
import { ChoiseEntrepriseComponent } from './choise-entreprise/choise-entreprise.component';
import { DashboardComponent } from './dashboard/dashboard.component';







const routes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: 'resetpassword', component: ResetpasswordComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  //home

    { path: 'choiseentreprise',component: ChoiseEntrepriseComponent, canActivate: [AuthGuard] ,},
    
     {path:'dashboard', component:DashboardComponent,canActivate: [AuthGuard]},
     
     { path: 'products', loadChildren: () => import('./products/products.module').then(m => m.ProductsModule) , canActivate: [AuthGuard]},
    
      { path: 'category', loadChildren: () => import('./category/category.module').then(m => m.CategoryModule), canActivate: [AuthGuard] },
    
      { path: 'revendeurs', loadChildren: () => import('./revendeurs/revendeurs.module').then(m => m.RevendeursModule) ,canActivate: [AuthGuard] },
      { path: 'home', loadChildren: () => import('./pages/home/home.module').then(m => m.HomeModule), canActivate: [AuthGuard] },
      //configurations
      { path: 'configurations', loadChildren: () => import('./configurations/configurations.module').then(m => m.ConfigurationsModule), canActivate: [AuthGuard] },
      //contact
      { path: 'support', loadChildren: () => import('./pages/support/support.module').then(m => m.SupportModule),canActivate: [AuthGuard]  },
      //lien utiles
      { path: 'liens-utiles', loadChildren: () => import('./liens-utiles/liens-utiles.module').then(m => m.LiensUtilesModule), canActivate: [AuthGuard] },
      //cookies
      { path: 'biscuits', loadChildren: () => import('./biscuits/biscuits.module').then(m => m.BiscuitsModule), },
     
      //Positioning Web
      { path: "posactuality", component: PosactualityComponent, canActivate: [AuthGuard] },
      { path: "Poscontacts", component: PoscontactsComponent, canActivate: [AuthGuard] },
      { path: "poscookies", component: PoscookiesComponent, canActivate: [AuthGuard] },
      { path: "posdatabase", component: PosdatabaseComponent, canActivate: [AuthGuard] },
      { path: "posdealers", component: PosdealersComponent, canActivate: [AuthGuard] },
      { path: "posmanagement", component: PosmanagementComponent, canActivate: [AuthGuard] },
      { path: "posnews", component: PosnewsComponent, canActivate: [AuthGuard] },
      { path: "posproducts", component: PosproductsComponent, canActivate: [AuthGuard] },
      { path: "possupport", component: PossupportComponent, canActivate: [AuthGuard] },
      { path: "posusefulllinks", component: PosusefulllinksComponent, canActivate: [AuthGuard] },
      { path: "poswhoarewe", component: PoswhorareweComponent, canActivate: [AuthGuard] },
      { path: "poshome", component: PoshomeComponent, canActivate: [AuthGuard] },
      { path: "posprivacy", component: PosprivacyComponent, canActivate: [AuthGuard] },
    
      //Home config
      { path: "homewelcometext", component: HomewelcometextComponent, canActivate: [AuthGuard] },
      { path: "homenewslist", component: HomenewslistComponent, canActivate: [AuthGuard] },
      { path: "homecustomerlogos", component: HomescustomerlogosComponent, canActivate: [AuthGuard] },
      { path: "homelist", component: HomelistComponent, canActivate: [AuthGuard] },
      { path: "homeprimaryslide", component: HomeprimaryslideComponent, canActivate: [AuthGuard] },
    
      //database
      { path: "database", component: DatabaseComponent, canActivate: [AuthGuard] },
      { path: "whoarewe", component: WhoareweComponent, canActivate: [AuthGuard] },
      { path: "privacypolicy", component: PrivacypolicyComponent, canActivate: [AuthGuard] },
      { path: "cookies", component: CookiesComponent, canActivate: [AuthGuard] },
      { path: "management", component: ManagementComponent, canActivate: [AuthGuard] },
      { path: "usefullinks", component: UsefulllinksComponent, canActivate: [AuthGuard] },
    
      //companybusiness
      { path: "company", component: CompanybusinessComponent, canActivate: [AuthGuard] },
    
      //account management
      { path: "account", component: AccountComponent, canActivate: [AuthGuard] },
    
      //cookies
      { path: "cookies-v2", component: CookiesV2Component, canActivate: [AuthGuard] },
      //Plateforme
      { path: "plateforme", component: PlateformeComponent, canActivate: [AuthGuard] },
    
    
      //board users admin moderateur for test authentification
      { path: 'user', component: BoardUserComponent, canActivate: [AuthGuard] },
      { path: 'mod', component: BoardModeratorComponent, canActivate: [AuthGuard] },
      { path: 'admin', component: BoardAdminComponent, canActivate: [AuthGuard] },
    
      //profile
      { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
    
    
      { path: 'groupe', loadChildren: () => import('./groupe/groupe.module').then(m => m.GroupeModule), canActivate: [AuthGuard] },
    
    
      { path: 'enterprise', loadChildren: () => import('./entreprise/entreprise.module').then(m => m.EntrepriseModule), canActivate: [AuthGuard] },
    
    
      { path: 'tags', loadChildren: () => import('./tags/tags.module').then(m => m.TagsModule), canActivate: [AuthGuard] },
    
    
      { path: 'language', loadChildren: () => import('./language/language.module').then(m => m.LanguageModule), canActivate: [AuthGuard] },
    
      { path: 'tap-to-top', loadChildren: () => import('./tap-to-top/tap-to-top.module').then(m => m.TapToTopModule), canActivate: [AuthGuard] },
    
      { path: 'cookies-notifier', loadChildren: () => import('./cookies-notifier/cookies-notifier.module').then(m => m.CookiesNotifierModule), canActivate: [AuthGuard] },
    
      { path: 'page', loadChildren: () => import('./web-positioning/web-positioning.module').then(m => m.WebPositioningModule), canActivate: [AuthGuard] },
    
      { path: 'assistance', loadChildren: () => import('./pages/assistance/assistance.module').then(m => m.AssistanceModule), canActivate: [AuthGuard] },
      

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
  exports: [RouterModule, MaterialModule],


})
export class AppRoutingModule { }
