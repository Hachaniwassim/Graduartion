import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CookiesNotifierComponent } from './cookies-notifier.component';

const routes: Routes = [{ path: '', component: CookiesNotifierComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CookiesNotifierRoutingModule { }
