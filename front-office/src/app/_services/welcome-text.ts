
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page3DTO } from '../models/dto/page3DTO';

@Injectable({
  providedIn: 'root'
})
/****
 * 
 * @author Tarchoun Abir
 * 
 */
export class HomeService {

  //api backend
  private base_url= environment.publicApi + "/page-home-public"

  constructor(private http :HttpClient) { }

//get by entreprise
getByEntreprise():any{
 return this.http.get<Page3DTO[]>(this.base_url + '/home-page/' + environment.enterpriseId);
}


// get by id
getById(id:number):Observable<Page3DTO>{
  return this.http.get<Page3DTO>(this.base_url + '/' +id);

}


 
}
