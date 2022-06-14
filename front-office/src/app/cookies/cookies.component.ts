import { Component, OnInit } from '@angular/core';
import { CookieDTO } from '../models/dto/cookieDTO';
import { CookiesService } from '../_services/cookies.service';


@Component({
  selector: 'app-cookies',
  templateUrl: './cookies.component.html',
  styleUrls: ['./cookies.component.css']
})


/**
 * 
 * @author Tarchoun Abir
 * 
 */
export class CookiesComponent implements OnInit {

 pageInfos !: CookieDTO;

  constructor( private cookiesService : CookiesService){ }


  ngOnInit(): void {
    this.getCookiesInfo();
    }
 

  async getCookiesInfo() {
    
    await this.cookiesService.getCurrentEnterpriseCookies()
      .subscribe((res: any) => {
        this.pageInfos=res[0];
        console.log("==========================> test",this.pageInfos)     
        
    });
  }

}
