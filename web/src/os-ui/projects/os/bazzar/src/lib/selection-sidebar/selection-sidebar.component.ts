import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'ngx-selection-sidebar',
  template: `
    <nb-card size="giant">
      <nb-card-body class="d-flex flex-column">
        <p class="font-weight-bold">Department</p>
        <nb-checkbox>Arts & Crafts</nb-checkbox>
        <nb-checkbox>Beauty</nb-checkbox>
        <nb-checkbox>Books</nb-checkbox>
        <nb-checkbox>Cell Phones & Accessories</nb-checkbox>
        <nb-checkbox>Computers & Accessories</nb-checkbox>
        <nb-checkbox>Electronics</nb-checkbox>
        <nb-checkbox>Fashion</nb-checkbox>
        <nb-checkbox>Kitchen</nb-checkbox>
        <nb-checkbox>Pet Supplies</nb-checkbox>
        <nb-checkbox>Everything Else</nb-checkbox>
        <p class="pt-3 font-weight-bold">Price</p>
        <a href="#">Under $25</a>
      </nb-card-body>
    </nb-card>
  `,
  styleUrls: ['./selection-sidebar.component.scss'],
})
export class SelectionSidebarComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
