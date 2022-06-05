import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { MaterialModule } from './material/material.module';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RegisterComponent } from './register/register.component';
import { ResetpasswordComponent } from './resetpassword/resetpassword.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NotfoundComponent } from './notfound/notfound.component';
import { ContactComponent } from './contact/contact.component';
import { UsefullLinksComponent } from './usefull-links/usefull-links.component';
import { AboutComponent } from './about/about.component';
import { PrivacyPolicyComponent } from './privacy-policy/privacy-policy.component';
import { CookiesComponent } from './cookies/cookies.component';
import { FooterComponent } from './footer/footer.component';
import { AssitanceComponent } from './assitance/assitance.component';
import { ProfileComponent } from './profile/profile.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthGuard } from './guard/auth.guard';
import { SimpleNotificationsModule } from 'angular2-notifications';
import { PostulsellerComponent } from './postulseller/postulseller.component';
import { SetGroupInterceptor } from './_helpers/set-group.interceptor';
import { NwesListComponent } from './nwes-list/nwes-list.component';
import { CategoryComponent } from './category/category.component';
import { TapToTopComponent } from './tap-to-top/tap-to-top.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    RegisterComponent,
    ResetpasswordComponent,
    LoginComponent,
    ContactComponent,
    UsefullLinksComponent,
    AboutComponent,
    PrivacyPolicyComponent,
    CookiesComponent,
    FooterComponent,
    AssitanceComponent,
    ProfileComponent,
    PostulsellerComponent,
    NwesListComponent,
    CategoryComponent,
    TapToTopComponent,
  ],
  imports: [
    ReactiveFormsModule,
    HttpClientModule,
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    SimpleNotificationsModule.forRoot(),

    
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [MaterialModule],
  providers: [  {
    provide: HTTP_INTERCEPTORS,
    useClass: SetGroupInterceptor,
    multi: true,

},],
  bootstrap: [AppComponent]
})
export class AppModule { }
