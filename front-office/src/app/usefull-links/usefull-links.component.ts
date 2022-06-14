import { Component, OnInit } from '@angular/core';

import { LinksDTO } from '../models/dto/linksDTO';
import { LinksService } from '../_services/links.service';

@Component({
  selector: 'app-usefull-links',
  templateUrl: './usefull-links.component.html',
  styleUrls: ['./usefull-links.component.css']
})
export class UsefullLinksComponent implements OnInit {
  
  pageInfos !: LinksDTO;

  constructor( private linkService : LinksService) { }

  ngOnInit(): void {
    this.getLinks();
   
  }


  async getLinks() {
    await this.linkService.getAlllinksByEntreprise()
      .subscribe((res: any) => {
        this.pageInfos=res[0];
        console.log("==========================> test",this.pageInfos)     
        
    });
  }

 
}
