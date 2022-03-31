import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QuiSommesNousComponent } from './qui-sommes-nous.component';

const routes: Routes = [{ path: '', component: QuiSommesNousComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QuiSommesNousRoutingModule { }
