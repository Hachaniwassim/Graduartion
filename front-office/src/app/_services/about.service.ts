
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page2DTO } from '../models/dto/page2DTO';

@Injectable({
  providedIn: 'root'
})
/****
 * 
 * @author Tarchoun Abir
 * 
 */
export class Aboutservice {

  //api backend
  private base_url= environment.publicApi + "/about-public"

  constructor(private http :HttpClient) { }

//get by entreprise
getByEntreprise():any{
 return this.http.get<Page2DTO[]>(this.base_url + '/about-page/' + environment.enterpriseId);
}


// get by id
getById(id:number):Observable<Page2DTO>{
  return this.http.get<Page2DTO>(this.base_url + '/' +id);

}


 
}
