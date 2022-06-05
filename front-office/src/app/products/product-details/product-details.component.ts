import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";

import { NotificationService } from 'src/app/shared/notification.service';
import { ProductService } from 'src/app/_services/products.service';
@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  //public voiture?: voiture;
  product={
    id: "",
    title:'', 
    detailimage: '',
    note: '',
    name: '',
    image: '',
    caracteristique:'',
    requirements :'',
    createdDate :'',
    lastModifiedDate:'',
    description : '',
    slug : ''
}
list : any ;
_id=this.route.snapshot.params['id'];

constructor(private route: ActivatedRoute , private productservice: ProductService) { }

ngOnInit(): void {
  this.getone();
 // this.route.paramMap.subscribe(params => {
  //  const voitureId = params.get("id");
  //  this.voiture = voitures.filter(voiture => voiture._id === voitureId)[0];
 // });
}
getone(){
  // status 304 ok  
   this.productservice.getByidproduct(this._id).subscribe((data)=>
   { this.list=data;
    this.product=this.list;
    console.log(this.product);
  })
 }
 reloadPage() {
  setTimeout(()=>{
      window.location.reload();
    }, 1000);  
}

}
