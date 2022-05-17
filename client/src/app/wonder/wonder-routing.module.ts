import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../core/auth-guard';
import { WonderComponent } from './wonder-component/wonder.component';

const routes: Routes = [
  { path: '', component: WonderComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WonderRoutingModule { }
