import {NgModule} from '@angular/core';

import {BazzarRoutingModule} from './bazzar-routing.module';
import {BazzarComponent} from './bazzar.component';
import {ThemeModule} from '../../../../../src/app/@theme/theme.module';
import {
  NbCardModule, NbCheckboxModule,
  NbInputModule,
  NbListModule,
  NbMenuModule,
  NbRouteTabsetModule, NbSelectModule,
  NbTabsetModule,
} from '@nebular/theme';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ProductComponent} from './dashboard/product/product.component';
import { TodayDealsComponent } from './today-deals/today-deals.component';
import { BestSellersComponent } from './best-sellers/best-sellers.component';
import { SelectionSidebarComponent } from './selection-sidebar/selection-sidebar.component';
import {FormsModule as ngFormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    BazzarComponent,
    DashboardComponent,
    ProductComponent,
    TodayDealsComponent,
    BestSellersComponent,
    SelectionSidebarComponent,
  ],
  imports: [
    ThemeModule,
    ngFormsModule,
    NbMenuModule,
    BazzarRoutingModule,
    NbListModule,
    NbCardModule,
    NbRouteTabsetModule,
    NbTabsetModule,
    NbInputModule,
    NbSelectModule,
    NbCheckboxModule,
  ],
})
export class BazzarModule {
}
