import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'ngx-my-bazzar',
  template: `
    <p>
      my-bazzar works!
    </p>
  `,
  styleUrls: ['bazzar-home.component.scss']
})
export class BazzarHomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
