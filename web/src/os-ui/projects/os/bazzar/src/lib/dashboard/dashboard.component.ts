import {Component} from '@angular/core';
import {BazzarService} from '../bazzar.service';

@Component({
  selector: 'ngx-dashboard',
  template: `
    <div class="row">
      <div class="col-md-12 col-lg-3 col-xl-3">
        <ngx-selection-sidebar></ngx-selection-sidebar>
      </div>
      <div class="col-md-9 col-lg-9 col-xl-9">
        <nb-card size="giant">
          <nb-tabset>
            <nb-tab tabTitle="Today's Deals">
              <ngx-today-deals></ngx-today-deals>
            </nb-tab>
            <nb-tab tabTitle="Best Sellers">
              <ngx-best-sellers></ngx-best-sellers>
            </nb-tab>
          </nb-tabset>
        </nb-card>
      </div>
    </div>
  `,
})
export class DashboardComponent {

  tabs: any[] = [
    {
      title: 'Today\'s Deals',
      route: '/bazzar/today/deals',
    },
    {
      title: 'Best Sellers',
      route: '/bazzar/top/sellers',
    },
  ];

  firstCard = {
    news: [],
    placeholders: [],
    loading: false,
    pageToLoadNext: 1,
  };
  secondCard = {
    news: [],
    placeholders: [],
    loading: false,
    pageToLoadNext: 1,
  };
  pageSize = 10;

  constructor(private bazzarService: BazzarService) {
  }

  loadNext(cardData) {
    if (cardData.loading) {
      return;
    }

    cardData.loading = true;
    cardData.placeholders = new Array(this.pageSize);
    this.bazzarService.load(cardData.pageToLoadNext, this.pageSize)
      .subscribe(nextNews => {
        cardData.placeholders = [];
        cardData.news.push(...nextNews);
        cardData.loading = false;
        cardData.pageToLoadNext++;
      });
  }

}
