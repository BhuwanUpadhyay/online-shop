import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BazzarComponent} from "./bazzar.component";
import {HomeBazzarComponent} from "./home/home.bazzar";
import {NotFoundComponent} from "../pages/miscellaneous/not-found/not-found.component";

const routes: Routes = [
  {
    path: '',
    component: BazzarComponent,
    children: [
      {
        path: 'home',
        component: HomeBazzarComponent,
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
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BazzarRoutingModule {
}
