import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable} from 'rxjs';
import { environment } from 'src/environments/environment';
import { pagesDTO } from '../models/dto/pagesDTO';

@Injectable({
  providedIn: 'root'
})
export class RevendeurService {

  //api backend
  private base_url= environment.publicApi +"/pages-public";
  
  constructor(private http :HttpClient) { }

//get  page  by enterprise 
 getAllPagesByEntreprise():any {
   return this.http.get<pagesDTO[]>(this.base_url + '/pages-revendeur/' + environment.enterpriseId);
 }


  // get page by id
  getByidPages(id:number):Observable<pagesDTO>{
    return this.http.get<pagesDTO>(this.base_url + '/' +id);

  }

  
}
