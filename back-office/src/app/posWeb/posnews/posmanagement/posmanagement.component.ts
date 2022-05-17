import { Component, OnInit } from '@angular/core';
import { poscontactes } from '../../poscontacts/poscontacts';
import { poscontacts } from '../../poscontacts/poscontactes.model';
@Component({
  selector: 'app-posmanagement',
  templateUrl: './posmanagement.component.html',
  styleUrls: ['./posmanagement.component.css']
})
export class PosmanagementComponent implements OnInit {
  public posmanagementlist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
