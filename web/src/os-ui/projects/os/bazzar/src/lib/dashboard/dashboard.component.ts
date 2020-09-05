import {Component} from '@angular/core';
import {BazzarService} from '../bazzar.service';

@Component({
  selector: 'ngx-dashboard',
  template: `
    <div class="infinite-cards row">
      <div class="col-md-12 col-lg-12 col-xl-12">
        <nb-card>
          <nb-card-header>Bazzar</nb-card-header>
          <nb-list
            nbInfiniteList
            listenWindowScroll
            [threshold]="500"
            (bottomThreshold)="loadNext(secondCard)">
            <nb-list-item *ngFor="let newsPost of secondCard.news">
              <ngx-product [post]="newsPost"></ngx-product>
            </nb-list-item>
          </nb-list>
        </nb-card>
      </div>
    </div>
  `,
})
export class DashboardComponent {

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
