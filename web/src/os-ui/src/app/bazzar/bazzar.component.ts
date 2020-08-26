import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'ngx-bazzar',
  template: `
    <ngx-container-layout>
      <router-outlet></router-outlet>
    </ngx-container-layout>
  `,
  styleUrls: ['bazzar.component.scss']
})
export class BazzarComponent {

}
