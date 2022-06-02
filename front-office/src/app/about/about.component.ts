import { Component, OnInit } from '@angular/core';
import { Aboutservice } from '../_services/about.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  public cookieslist: any = [];
  constructor( private aboutservice : Aboutservice) { }

  ngOnInit(): void {
    this.aboutservice.getAllaboutByEntreprise().subscribe(
      res => this.cookieslist = res
    );
  }
}
