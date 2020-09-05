import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BazzarComponent} from './bazzar.component';
import {NotFoundComponent} from '../../../../../src/app/pages/miscellaneous/not-found/not-found.component';
import {DashboardComponent} from './dashboard/dashboard.component';

const routes: Routes = [
  {
    path: '',
    component: BazzarComponent,
    children: [
      {
        path: 'home',
        component: DashboardComponent,
      },
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full',
      },
      {
        path: '**',
        component: NotFoundComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BazzarRoutingModule {
}
