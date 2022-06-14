import { Component, OnInit } from '@angular/core';
import { Newsservice } from 'src/app/_services/news.service';
import { Page3DTO } from '../models/dto/page3DTO';
import { ProductService } from '../_services/products.service';
import { HomeService } from '../_services/welcome-text';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  pageInfos !:Page3DTO;
  public productslist: any = [];
  constructor( private productservice : ProductService, private homeService: HomeService) { }
 
 ngOnInit(): void {
 
   this.getProduct();
   this.getInfo();
 
  }
 
 
  getProduct(){
   this.productservice.getAllproductsByEntreprise().subscribe(
     ( res : any) => this.productslist = res
   );
  }

  async getInfo() {
    
    await this.homeService.getByEntreprise()
      .subscribe((res: any) => {
        this.pageInfos=res[0];
        console.log("==========================> test",this.pageInfos)     
        
    });
  }
}
