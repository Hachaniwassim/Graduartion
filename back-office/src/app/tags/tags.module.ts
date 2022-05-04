import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TagsRoutingModule } from './tags-routing.module';
import { TagsComponent } from './tags.component';
import { ListTagsComponent } from './list-tags/list-tags.component';
import { AddTagsComponent } from './add-tags/add-tags.component';


@NgModule({
  declarations: [
    TagsComponent,
    ListTagsComponent,
    AddTagsComponent
  ],
  imports: [
    CommonModule,
    TagsRoutingModule
  ]
})
export class TagsModule { }
