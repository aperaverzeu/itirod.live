import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { KnowlageRoutingModule } from './knowlage-routing.module';
import { SharedModule } from '../shared/shared.module';
import { KnowlageMenuComponent } from './knowlage-menu-component/knowlage-menu.component';
import { CourseComponent } from './knowlage-menu-component/course-component/course.component';
import { PostComponent } from './knowlage-menu-component/course-component/post-component/post.component';


@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    KnowlageRoutingModule
  ],
  declarations: [KnowlageMenuComponent, CourseComponent, PostComponent],
  providers: []
})
export class KnowlageModule { }
