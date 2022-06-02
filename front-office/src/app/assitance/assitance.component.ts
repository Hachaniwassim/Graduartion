import { Component, OnInit } from '@angular/core';
import { Assistanceservice } from '../_services/assistance.service';

@Component({
  selector: 'app-assitance',
  templateUrl: './assitance.component.html',
  styleUrls: ['./assitance.component.css']
})
export class AssitanceComponent implements OnInit {

  public cookieslist: any = [];
  constructor( private assistanceservice : Assistanceservice) { }

  ngOnInit(): void {
    this.assistanceservice.getassistanceByEntreprise().subscribe(
      res => this.cookieslist = res
    );
  }
}
