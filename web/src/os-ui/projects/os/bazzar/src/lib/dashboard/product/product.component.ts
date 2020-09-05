import {Component, Input} from '@angular/core';
import {NewsPost} from '../../bazzar.service';

@Component({
  selector: 'ngx-product',
  template: `
    <article>
      <h2>{{post.title}}</h2>
      <p>{{post.text}}</p>
      <a [attr.href]="post.link">Read full article</a>
    </article>
  `,
  styleUrls: ['./product.component.scss'],
})
export class ProductComponent {

  @Input() post: NewsPost;

}
