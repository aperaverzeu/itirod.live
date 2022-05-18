import {NgModule, Optional, SkipSelf} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {AuthGuard} from './auth-guard';
import {AuthService} from './auth.service';
import {TokenStorage} from './token-storage';
import {RouterModule} from '@angular/router';
import {LoadGuard} from './load-guard';
import { UsernameStorage } from './user-storage';

@NgModule({
  imports: [CommonModule, HttpClientModule, RouterModule],
  providers: [
    AuthGuard,
    LoadGuard,
    AuthService,
    TokenStorage,
    UsernameStorage
  ],
  declarations: [],
})
export class CoreModule {
  // Prevent reimport of the CoreModule
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule is already loaded. Import it in the AppModule only');
    }
  }
}
