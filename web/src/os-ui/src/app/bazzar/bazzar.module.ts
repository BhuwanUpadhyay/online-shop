import {NgModule} from '@angular/core';

import {BazzarRoutingModule} from './bazzar-routing.module';
import {BazzarComponent} from './bazzar.component';
import {BazzarHomeComponent} from './home/bazzar-home.component';
import {ThemeModule} from "../@theme/theme.module";
import {NbMenuModule} from "@nebular/theme";


@NgModule({
  declarations: [
    BazzarComponent,
    BazzarHomeComponent
  ],
  imports: [
    ThemeModule,
    NbMenuModule,
    BazzarRoutingModule
  ]
})
export class BazzarModule {
}
