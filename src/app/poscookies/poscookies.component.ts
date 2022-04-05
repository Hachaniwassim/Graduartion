import { Component, OnInit } from '@angular/core';
import { poscontactes } from '../poscontacts/poscontacts';
import { poscontacts } from '../poscontacts/poscontactes.model';
@Component({
  selector: 'app-poscookies',
  templateUrl: './poscookies.component.html',
  styleUrls: ['./poscookies.component.css']
})
export class PoscookiesComponent implements OnInit {
  public poscookieslist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
