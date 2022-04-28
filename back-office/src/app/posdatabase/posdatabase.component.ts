import { Component, OnInit } from '@angular/core';
import { poscontactes } from '../poscontacts/poscontacts';
import { poscontacts } from '../poscontacts/poscontactes.model';
@Component({
  selector: 'app-posdatabase',
  templateUrl: './posdatabase.component.html',
  styleUrls: ['./posdatabase.component.css']
})
export class PosdatabaseComponent implements OnInit {
  public posdatabaselist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
