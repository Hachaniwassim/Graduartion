import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PostRevendeurDTO } from '../models/dto/post-revebdeurDTO';
import { productsDTO } from '../models/dto/productsDTO';
import { ProductService } from './products.service';

@Injectable({
  providedIn: 'root'
})
/**
 * 
 * @author Tarchoun Abir
 * 
 */
export class ContactService {

  //api backend
  private base_url=environment.publicApi + '/post-revendeurs-public';
  private product_url= environment.publicApi +'/product-public';
  
  constructor(private http :HttpClient , private productService : ProductService) { }
  
  //get all by entreprise
getByEntreprise():any{
  return this.http.get<PostRevendeurDTO[]>(this.base_url + '/list-form-revendeurs/'+environment.enterpriseId);
 }
 
 
 // get by id
 getByid(id:number):Observable<PostRevendeurDTO>{
   return this.http.get<PostRevendeurDTO>(this.base_url + '/' +id);
 
 }
 create(item :any):any{
  return this.http.post<PostRevendeurDTO>(this.base_url +  '/post-form',item);
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
  mobile : new FormControl('',Validators.required),
  cap: new FormControl(''),
  productId : new FormControl('')
});



}
