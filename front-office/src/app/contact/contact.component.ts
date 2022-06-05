import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { productsDTO } from '../models/dto/productsDTO';
import { ContactService } from '../_services/contact.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  productServices: productsDTO[] = [];
  constructor( public contactService : ContactService) { }

  ngOnInit(): void {

    this.getProduct();

  }

   getProduct(){
     this.contactService.getAllproductsByEntreprise().subscribe((res: any)=>{
     console.log("===================> Products:", res)
     this.productServices=res;
     
    })
   }

  onSubmit() {
    if (this.contactService.form.valid) {
        this.contactService.create({
          id:this.contactService.form.value.id,
          message:this.contactService.form.value.message,
          createdDate:new Date(),
          lastModifiedDate:new Date(),
          adresse:this.contactService.form.value.adresse,
          mobile:this.contactService.form.value.mobile,
          entrepriseId:environment.enterpriseId,
          productId:this.contactService.form.value.productId,
          companyname:this.contactService.form.value.companyname,
          email:this.contactService.form.value.email,
          nationality:this.contactService.form.value.nationality,
          referent:this.contactService.form.value.referent,
          fax:this.contactService.form.value.fax,

        }).subscribe((res: any) => {
        
          console.log('the result of post contact =============>',res)
          
        })
      }
    }
    reloadPage() {
      window.location.reload();
    }

}
