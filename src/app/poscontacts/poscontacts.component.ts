import { Component, OnInit } from '@angular/core';
import { poscontactes } from './poscontacts';
import { poscontacts } from './poscontactes.model';
@Component({
  selector: 'app-poscontacts',
  templateUrl: './poscontacts.component.html',
  styleUrls: ['./poscontacts.component.css']
})
export class PoscontactsComponent implements OnInit {

  public poscontactslist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
