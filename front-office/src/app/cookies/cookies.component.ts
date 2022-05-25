import { Component, OnInit } from '@angular/core';
import { CookiesService } from '../_services/cookies.service';

@Component({
  selector: 'app-cookies',
  templateUrl: './cookies.component.html',
  styleUrls: ['./cookies.component.css']
})
export class CookiesComponent implements OnInit {

  public cookieslist: any = [];
  constructor( private cookiesService : CookiesService) { }

  ngOnInit(): void {
    this.cookiesService.getCurrentEnterpriseCookies().subscribe(
      res => this.cookieslist = res
    );
  }
}
