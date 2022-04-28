import { Component, OnInit } from '@angular/core';
import { poscontactes } from '../poscontacts/poscontacts';
import { poscontacts } from '../poscontacts/poscontactes.model';
@Component({
  selector: 'app-posprivacy',
  templateUrl: './posprivacy.component.html',
  styleUrls: ['./posprivacy.component.css']
})
export class PosprivacyComponent implements OnInit {
  public posprivacylist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
