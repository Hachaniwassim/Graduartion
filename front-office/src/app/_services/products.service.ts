
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { productsDTO } from '../models/dto/productsDTO';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  //api backend
  private base_url= environment.publicApi + "/product-public";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http :HttpClient) { }

  
//get all by entreprise
getAllproductsByEntreprise():any{
 return this.http.get<productsDTO[]>(this.base_url  + '/list-products/' +  environment.enterpriseId);
}


// get by id
getByidproduct(id:number):Observable<productsDTO>{
  return this.http.get<productsDTO>(this.base_url + '/' +id);

}


getTagsByproduct(id:number):Observable<productsDTO>{
  return this.http.get<productsDTO>(this.base_url + '/get-tags-by-product' + '/' +id);

}


}
 