import { Component, OnInit } from '@angular/core';
import { Page2DTO } from '../models/dto/page2DTO';
import { Aboutservice } from '../_services/about.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})

/********
 * 
 * @author Tarchoun Abir
 * 
 */
export class AboutComponent implements OnInit {

  pageInfos !: Page2DTO; 

  constructor( private aboutservice : Aboutservice) { }

  ngOnInit(): void {
  this.getPageInfo()
  }


  async getPageInfo() {
    await this.aboutservice.getByEntreprise()
      .subscribe((res: any) => {
        this.pageInfos=res[0];
        console.log("==========================> test",this.pageInfos);   
        
    });
  }
}
