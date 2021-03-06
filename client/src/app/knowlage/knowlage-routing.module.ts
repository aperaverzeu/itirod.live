import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../core/auth-guard';
import { CourseComponent } from './knowlage-menu-component/course-component/course.component';
import { PostComponent } from './knowlage-menu-component/course-component/post-component/post.component';
import { KnowlageMenuComponent } from './knowlage-menu-component/knowlage-menu.component';

const routes: Routes = [
  { path: '', component: KnowlageMenuComponent, canActivate: [AuthGuard] },
  { path: ':course', component: CourseComponent, canActivate: [AuthGuard] },
  { path: ':course/:post', component: PostComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class KnowlageRoutingModule { }
