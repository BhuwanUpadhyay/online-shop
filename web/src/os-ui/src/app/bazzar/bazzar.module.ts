import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {BazzarRoutingModule} from './bazzar-routing.module';
import {BazzarComponent} from './bazzar.component';


@NgModule({
  declarations: [BazzarComponent],
  imports: [
    CommonModule,
    BazzarRoutingModule
  ]
})
export class BazzarModule {
}
