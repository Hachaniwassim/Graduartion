import { Component, OnInit } from '@angular/core';
import { poscontactes } from '../poscontacts/poscontacts';
import { poscontacts } from '../poscontacts/poscontactes.model';
@Component({
  selector: 'app-possupport',
  templateUrl: './possupport.component.html',
  styleUrls: ['./possupport.component.css']
})
export class PossupportComponent implements OnInit {
  public possupportlist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
