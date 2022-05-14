import { Component } from '@angular/core';
import {LoadingBarService} from '@ngx-loading-bar/core';
import {delay, map, withLatestFrom} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title(title: any) {
    throw new Error('Method not implemented.');
  }
 
    // For Progressbar
    loaders = this.loader.progress$.pipe(
      delay(1000),
      withLatestFrom(this.loader.progress$),
      map(v => v[1]),
  );

  showDefaultCookiesNotifier: boolean = false;

  constructor( private loader: LoadingBarService) {}

  ngOnInit() {
    this.showDefaultCookiesNotifier= true;
  }
}