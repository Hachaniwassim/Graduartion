import { Component, OnInit } from '@angular/core';
import { Linksservice } from 'src/app/_services/links.service';

@Component({
  selector: 'app-usefull-links',
  templateUrl: './usefull-links.component.html',
  styleUrls: ['./usefull-links.component.css']
})
export class UsefullLinksComponent implements OnInit {

  public linkslist: any = [];
  constructor( private linkservice : Linksservice) { }
 
 ngOnInit(): void {
    this.linkservice.getAlllinksByEntreprise().subscribe(
      res => this.linkslist = res
    );
  }
 
}
