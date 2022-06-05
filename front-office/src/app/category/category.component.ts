import { Component, OnInit } from '@angular/core';
import { categoryDTO } from '../models/dto/categoryDTO';
import { CategoryService } from '../_services/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categoryList : categoryDTO []= [];
  constructor( private Categoryservice : CategoryService) { }

  ngOnInit(): void {
    this.getCategory()
  }
  getCategory(){
    this.Categoryservice.getallCategorieByEntreprise().subscribe(
      ( res : any) =>
       this.categoryList = res,
       
    )
   }

}
