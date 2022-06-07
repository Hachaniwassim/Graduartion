
import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable} from 'rxjs';
import { environment } from 'src/environments/environment';
import { postDTO } from '../models/dto/postDTO';

@Injectable({
  providedIn: 'root'
})

/**
 * 
 * @author Tarchoun Abir
 * 
 */
export class PostService {

  //api backend
  private base_url= environment.publicApi + "/Reference-public";
  

  constructor(private http :HttpClient) { }


//get all   refrence by entreprise
getAllByEntreprise():any{
   return this.http.get<postDTO[]>(this.base_url+ '/list-refrence/'+ environment.enterpriseId)
 }


  // get refrence  by id
  getByid(id:number):Observable<postDTO>{
    return this.http.get<postDTO>(this.base_url + '/' +id);

  }

   
}
