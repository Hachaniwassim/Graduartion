import { Component, OnInit } from '@angular/core';
import { poscontactes } from '../poscontacts/poscontacts';
import { poscontacts } from '../poscontacts/poscontactes.model';
@Component({
  selector: 'app-poshome',
  templateUrl: './poshome.component.html',
  styleUrls: ['./poshome.component.css']
})
export class PoshomeComponent implements OnInit {
  public poshomelist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
