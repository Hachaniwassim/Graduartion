import { Component, OnInit } from '@angular/core';
import { poscontacts } from '../poscontacts/poscontactes.model';
import { poscontactes } from '../poscontacts/poscontacts';
@Component({
  selector: 'app-posactuality',
  templateUrl: './posactuality.component.html',
  styleUrls: ['./posactuality.component.css']
})
export class PosactualityComponent implements OnInit {
  public posactualitylist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
