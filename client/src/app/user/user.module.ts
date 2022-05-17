import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { ProfileComponent } from './profile/profile.component';
import { SharedModule } from '../shared/shared.module';
import { ProfileService } from './profile.service';
import { NgApexchartsModule } from "ng-apexcharts";


@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    UserRoutingModule, 
    NgApexchartsModule
  ],
  declarations: [ProfileComponent],
  providers: [ProfileService]
})
export class UserModule { }
