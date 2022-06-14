import { Component, OnInit } from '@angular/core';
import { privacyDTO } from '../models/dto/privacyDTO';
import { Privacyservice } from '../_services/privacy.service';

@Component({
  selector: 'app-privacy-policy',
  templateUrl: './privacy-policy.component.html',
  styleUrls: ['./privacy-policy.component.css']
})
export class PrivacyPolicyComponent implements OnInit {
  public pageInfos !: privacyDTO;
  constructor( private privacyservice : Privacyservice) { }

  ngOnInit(): void {
    this.getPageInfo();
  }
  
  async getPageInfo() {
    await this.privacyservice.getallPrivacy()
      .subscribe((res: any) => {
        this.pageInfos=res[0]
        console.log("==========================> test",this.pageInfos)     
        
    });
  }
}
