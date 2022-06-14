import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from './products.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const routes: Routes = [{ path: '', component: ProductsComponent },
{ path: ':id', component: ProductDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes) , 
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,

  
  ],
  exports: [RouterModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,

        ],
  declarations: [
    ProductDetailsComponent,
  ]
  
})
export class ProductsRoutingModule { }
