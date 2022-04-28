import { Component, OnInit } from '@angular/core';
import { poscontactes } from '../poscontacts/poscontacts';
import { poscontacts } from '../poscontacts/poscontactes.model';
@Component({
  selector: 'app-posproducts',
  templateUrl: './posproducts.component.html',
  styleUrls: ['./posproducts.component.css']
})
export class PosproductsComponent implements OnInit {
  public posproductslist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
