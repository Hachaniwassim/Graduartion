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
import { AboutComponent } from "./about/about.component";
import { UsefullLinksComponent } from "./usefull-links/usefull-links.component";
import { ContactComponent } from "./contact/contact.component";
import { CookiesComponent } from "./cookies/cookies.component";
import { PrivacyPolicyComponent } from "./privacy-policy/privacy-policy.component";
import { AssitanceComponent } from "./assitance/assitance.component";
import { ProfileComponent } from "./profile/profile.component";
import { AuthGuard } from './guard/auth.guard';
import { PostulsellerComponent } from "./postulseller/postulseller.component";
import { NwesListComponent } from './nwes-list/nwes-list.component';
import { CategoryComponent } from './category/category.component';


const routes: Routes = [



  { path: "home", component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'resetpassword', component: ResetpasswordComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  //home


  { path: 'products', loadChildren: () => import('./products/products.module').then(m => m.ProductsModule) },

  { path: 'sellers', loadChildren: () => import('./sellers/sellers.module').then(m => m.SellersModule) },

  { path: 'cookies-notifier', loadChildren: () => import('./cookies-notifier/cookies-notifier.module').then(m => m.CookiesNotifierModule) },

  //About
  { path: 'about', component: AboutComponent },

  { path: 'usefulllinks', component: UsefullLinksComponent },

  { path: 'contact', component: ContactComponent },

  { path: 'cookies', component: CookiesComponent },

  { path: 'privacypolicy', component: PrivacyPolicyComponent },

  { path: 'nwes', component: NwesListComponent },

  { path: 'assistance', component: AssitanceComponent },

  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  {path : 'category', component : CategoryComponent},

  { path: 'postulseller', component: PostulsellerComponent, canActivate: [AuthGuard] },




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
  exports: [RouterModule, MaterialModule]
})
export class AppRoutingModule { }
