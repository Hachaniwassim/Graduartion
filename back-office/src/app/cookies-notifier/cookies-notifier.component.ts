import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cookies-notifier',
  templateUrl: './cookies-notifier.component.html',
  styleUrls: ['./cookies-notifier.component.css']
})
export class CookiesNotifierComponent implements OnInit {

  constructor() { }

  showCookiesNotifier: boolean = false;


  ngOnInit(): void {
    const acceptCookies  = localStorage.getItem('acceptCookies') ;
    this.showCookiesNotifier = !acceptCookies;
  }

  acceptCookies() {
    localStorage.setItem('acceptCookies', 'accept');
    this.close();
  }

  declineCookies() {
    localStorage.setItem('acceptCookies', 'decline');
    this.close();
  }

  close() {
    this.showCookiesNotifier = false;
  }



}



