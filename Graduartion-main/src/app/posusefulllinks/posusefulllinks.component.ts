import { Component, OnInit } from '@angular/core';
import { poscontactes } from '../poscontacts/poscontacts';
import { poscontacts } from '../poscontacts/poscontactes.model';
@Component({
  selector: 'app-posusefulllinks',
  templateUrl: './posusefulllinks.component.html',
  styleUrls: ['./posusefulllinks.component.css']
})
export class PosusefulllinksComponent implements OnInit {
  public posusefulllinkslist: poscontacts[] = poscontactes;

  constructor() { }

  ngOnInit(): void {
  }

}
