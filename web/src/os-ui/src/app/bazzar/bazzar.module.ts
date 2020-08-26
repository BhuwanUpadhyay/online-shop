import {NgModule} from '@angular/core';

import {BazzarRoutingModule} from './bazzar-routing.module';
import {BazzarComponent} from './bazzar.component';
import {HomeBazzarComponent} from './home/home.bazzar';
import {ThemeModule} from "../@theme/theme.module";
import {NbMenuModule} from "@nebular/theme";


@NgModule({
  declarations: [
    BazzarComponent,
    HomeBazzarComponent
  ],
  imports: [
    ThemeModule,
    NbMenuModule,
    BazzarRoutingModule
  ]
})
export class BazzarModule {
}
