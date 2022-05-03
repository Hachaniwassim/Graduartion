import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LeftSlideBarComponent } from './left-slide-bar/left-slide-bar.component';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
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
import { HomewelcometextComponent } from './homewelcometext/homewelcometext.component';
import { HomenewslistComponent } from './homenewslist/homenewslist.component';
import { HomescustomerlogosComponent } from './homescustomerlogos/homescustomerlogos.component';
import { HomelistComponent } from './homelist/homelist.component';
import { HomeprimaryslideComponent } from './homeprimaryslide/homeprimaryslide.component';
import { WhoareweComponent } from './whoarewe/whoarewe.component';
import { DatabaseComponent } from './database/database.component';
import { CookiesComponent } from './cookies/cookies.component';
import { ManagementComponent } from './management/management.component';
import { UsefulllinksComponent } from './usefulllinks/usefulllinks.component';
import { MatConfirmDialogComponent } from './mat-confirm-dialog/mat-confirm-dialog.component';
import { MatDialogRef } from '@angular/material/dialog';
import { DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { ProfileComponent } from './profile/profile.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { NgxCaptchaModule } from 'ngx-captcha';
import { CompanybusinessComponent } from './companybusiness/companybusiness.component';
import { CompanyListComponent } from './companybusiness/company-list/company-list.component';
import { CompanyAddComponent } from './companybusiness/company-add/company-add.component';
import { AccountComponent } from './account/account.component';
import { AccountEditComponent } from './account/account-edit/account-edit.component';
import { AccountListComponent } from './account/account-list/account-list.component';
import { AccountViewComponent } from './account/account-view/account-view.component';
import { AgendaService, DayService, MonthAgendaService, MonthService, ScheduleModule, TimelineMonthService, TimelineViewsService, WeekService, WorkWeekService } from '@syncfusion/ej2-angular-schedule';
import { SimpleNotificationsModule } from 'angular2-notifications';
import { CompanyViewComponent } from './companybusiness/company-view/company-view.component';
import { CookiesV2Component } from './cookies-v2/cookies-v2.component';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { PosproductlistViewComponent } from './posproductlist/posproductlist-view/posproductlist-view.component';
import { PosproductlistAddComponent} from './posproductlist/posproductlist-add/posproductlist-add.component';
import { PosListComponent } from './posproductlist/pos-list/pos-list.component';
import { PlateformeComponent } from './plateforme/plateforme.component';
import { PlateformeListComponent } from './plateforme/plateforme-list/plateforme-list.component';
import { PlateformeViewComponent } from './plateforme/plateforme-view/plateforme-view.component';
import { PlateformeAddComponent } from './plateforme/plateforme-add/plateforme-add.component';
import { NgxSpinnerModule } from "ngx-spinner"; 
import { CKEditorModule } from 'ckeditor4-angular';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LeftSlideBarComponent,
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
    HomewelcometextComponent,
    HomenewslistComponent,
    HomescustomerlogosComponent,
    HomelistComponent,
    HomeprimaryslideComponent,
    WhoareweComponent,
    DatabaseComponent,
    CookiesComponent,
    ManagementComponent,
    UsefulllinksComponent,
    MatConfirmDialogComponent,
    LoginComponent,
    RegisterComponent,
    BoardUserComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    ProfileComponent,
    CompanybusinessComponent,
    CompanyListComponent,
    CompanyAddComponent,
    AccountComponent,
    AccountEditComponent,
    AccountListComponent,
    AccountViewComponent,
    CompanyViewComponent,
    CookiesV2Component,
    PosproductlistViewComponent,
    PosproductlistAddComponent,
    PosListComponent,
    PlateformeComponent,
    PlateformeListComponent,
    PlateformeViewComponent,
    PlateformeAddComponent,
  
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule,
    NgxCaptchaModule,
    FormsModule, 
    ScheduleModule, 
    SimpleNotificationsModule.forRoot(),
    AngularEditorModule,
    NgxSpinnerModule,
    CKEditorModule,
    


  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [MaterialModule, ScheduleModule,NgxSpinnerModule],
  providers: [
    {provide: MatDialogRef, useValue: {close: (_dialogResult: any) => { }} }, DatePipe, authInterceptorProviders,DayService, WeekService, WorkWeekService, MonthService, AgendaService, MonthAgendaService, TimelineViewsService, TimelineMonthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }