import { Component, OnInit } from '@angular/core';
import { products } from './posproductlist';
import { product } from './posproductlist.model';
@Component({
  selector: 'app-posproductlist',
  templateUrl: './posproductlist.component.html',
  styleUrls: ['./posproductlist.component.css']
})
export class PosproductlistComponent implements OnInit {
  public productsList: product[] = products;

  constructor() { }

  ngOnInit(): void {
  }

}
