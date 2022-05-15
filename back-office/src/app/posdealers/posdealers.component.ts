import { Component, OnInit } from '@angular/core';
import { poscontactes } from '../poscontacts/poscontacts';
import { poscontacts } from '../poscontacts/poscontactes.model';
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
