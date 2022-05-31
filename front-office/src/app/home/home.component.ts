import { Component, OnInit } from '@angular/core';
import { Newsservice } from 'src/app/_services/news.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public newslist: any = [];
  constructor( private newsservice : Newsservice) { }
 
 ngOnInit(): void {
    this.newsservice.getAllnewsByEntreprise().subscribe(
      res => this.newslist = res
    );
  }
}
