import {NgModule} from '@angular/core';

import {BazzarRoutingModule} from './bazzar-routing.module';
import {BazzarComponent} from './bazzar.component';
import {ThemeModule} from '../../../../../src/app/@theme/theme.module';
import {NbCardModule, NbListModule, NbMenuModule} from '@nebular/theme';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ProductComponent} from './dashboard/product/product.component';


@NgModule({
  declarations: [
    BazzarComponent,
    DashboardComponent,
    ProductComponent,
  ],
  imports: [
    ThemeModule,
    NbMenuModule,
    BazzarRoutingModule,
    NbListModule,
    NbCardModule,
  ],
})
export class BazzarModule {
}
