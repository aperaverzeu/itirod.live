import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WonderRoutingModule } from './wonder-routing.module';
import { SharedModule } from '../shared/shared.module';
import { WonderService } from './wonder.service';
import { WonderComponent } from './wonder-component/wonder.component';


@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    WonderRoutingModule
  ],
  declarations: [WonderComponent],
  providers: [WonderService]
})
export class WonderModule { }
