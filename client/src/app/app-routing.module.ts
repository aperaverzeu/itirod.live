import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoadGuard } from './core/load-guard';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'user', loadChildren: () => import('./user/user.module').then((user) => user.UserModule) },
  { path: 'wonder', loadChildren: () => import('./wonder/wonder.module').then((wonder) => wonder.WonderModule) },
  { path: 'knowlage', loadChildren: () => import('./knowlage/knowlage.module').then((knowlage) => knowlage.KnowlageModule) },
  {
    path: 'auth',
    // loadChildren: './auth/auth.module#AuthModule',
    loadChildren: () => import('./auth/auth.module').then((auth) => auth.AuthModule),
    canLoad: [LoadGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
