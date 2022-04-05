import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LeftSlideBarComponent } from './left-slide-bar/left-slide-bar.component';
import { MainTemplateComponent } from './main-template/main-template.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { MaterialModule } from './material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PossupportComponent } from './possupport/possupport.component';
import { PoscontactsComponent } from './poscontacts/poscontacts.component';
import { PoshomeComponent } from './poshome/poshome.component';
import { PosactualityComponent } from './posactuality/posactuality.component';
import { PosnewsComponent } from './posnews/posnews.component';
import { PosproductsComponent } from './posproducts/posproducts.component';
import { PosproductlistComponent } from './posproductlist/posproductlist.component';
import { PosmanagementComponent } from './posmanagement/posmanagement.component';
import { PosdealersComponent } from './posdealers/posdealers.component';
import { PoswhorareweComponent } from './poswhorarewe/poswhorarewe.component';
import { PosusefulllinksComponent } from './posusefulllinks/posusefulllinks.component';
import { PosdatabaseComponent } from './posdatabase/posdatabase.component';
import { PoscookiesComponent } from './poscookies/poscookies.component';
import { PrivacypolicyComponent } from './privacypolicy/privacypolicy.component';
import { PosprivacyComponent } from './posprivacy/posprivacy.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LeftSlideBarComponent,
    NotfoundComponent,
    PossupportComponent,
    PoscontactsComponent,
    PoshomeComponent,
    PosactualityComponent,
    PosnewsComponent,
    PosproductsComponent,
    PosproductlistComponent,
    PosmanagementComponent,
    PosdealersComponent,
    PoswhorareweComponent,
    PosusefulllinksComponent,
    PosdatabaseComponent,
    PoscookiesComponent,
    PrivacypolicyComponent,
    PosprivacyComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
