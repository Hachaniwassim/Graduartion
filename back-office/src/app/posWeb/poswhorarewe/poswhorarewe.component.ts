import { Component, OnInit } from '@angular/core';
import { poscontactes } from '../poscontacts/poscontacts';
import { poscontacts } from '../poscontacts/poscontactes.model';
@Component({
  selector: 'app-poswhorarewe',
  templateUrl: './poswhorarewe.component.html',
  styleUrls: ['./poswhorarewe.component.css']
})
export class PoswhorareweComponent implements OnInit {
  public poswhoarewelist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
