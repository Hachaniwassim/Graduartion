import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import {FormEntityDTO } from '../models/dto/formEntityDTO';
import { productsDTO } from '../models/dto/productsDTO';
import { ProductService } from './products.service';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  //api backend
  private base_url=environment.publicApi + '/contact-public';
  private product_url= environment.publicApi +'/product-public';
  
  constructor(private http :HttpClient , private productService : ProductService) { }
  //get all by entreprise
getByEntreprise():any{
  return this.http.get<FormEntityDTO[]>(this.base_url + '/list-form/'+environment.enterpriseId);
 }
 
 
 // get by id
 getByid(id:number):Observable<FormEntityDTO>{
   return this.http.get<FormEntityDTO>(this.base_url + '/' +id);
 
 }
 create(item :any):any{
  return this.http.post<FormEntityDTO>(this.base_url +  '/post-form',item);
}

getAllproductsByEntreprise():any{
  return this.http.get<productsDTO[]>(this.product_url + '/list-products/' +  environment.enterpriseId);
 }
 //validation formulaire
  form : FormGroup= new FormGroup({
  id: new FormControl(null),
  entrepriseId: new FormControl(null),
  companyname: new FormControl('',Validators.required),
  createdDate:new FormControl(''),
  lastModifiedDate:new FormControl(''),
  referent : new FormControl(''),
  adresse : new FormControl(''),
  message: new FormControl(''),
  email : new FormControl('',[Validators.required,Validators.email]),
  nationality: new FormControl(''),
  mobile : new FormControl(''),
  fax : new FormControl(''),
  productId : new FormControl('')


  

});



}
