import { Component, OnInit } from '@angular/core';
import { poscontacts } from '../poscontacts/poscontactes.model';
import { poscontactes } from '../poscontacts/poscontacts';
@Component({
  selector: 'app-posdealers',
  templateUrl: './posdealers.component.html',
  styleUrls: ['./posdealers.component.css']
})
export class PosdealersComponent implements OnInit {
  public posdealerslist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
