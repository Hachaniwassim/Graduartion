
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NwesDTO } from '../models/dto/nwesDTO';

@Injectable({
  providedIn: 'root'
})
export class Newsservice {

  //api backend
  private base_url= environment.publicApi + "/nwes-public";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');


  constructor(private http :HttpClient) { }

  
//get all by entreprise id
getAllnewsByEntreprise():any{
 return this.http.get<NwesDTO>(this.base_url + '/list-nwes/' + environment.enterpriseId);
}


// get by id
getByidnew(id:number):Observable<NwesDTO>{
  return this.http.get<NwesDTO>(this.base_url + '/' +id);

}


 
}
