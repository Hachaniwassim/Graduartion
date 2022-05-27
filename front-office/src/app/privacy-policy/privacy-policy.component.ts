import { Component, OnInit } from '@angular/core';
import { Privacyservice } from '../_services/privacy.service';

@Component({
  selector: 'app-privacy-policy',
  templateUrl: './privacy-policy.component.html',
  styleUrls: ['./privacy-policy.component.css']
})
export class PrivacyPolicyComponent implements OnInit {
  public privacylist: any = [];
  constructor( private privacyservice : Privacyservice) { }

  ngOnInit(): void {
    this.privacyservice. getallPrivacy().subscribe(
      res => this.privacylist = res
    );
  }

}
