
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NwesDTO } from '../models/dto/nwesDTO';

@Injectable({
  providedIn: 'root'
})

/**
 * 
 * @author Tarchoun Abir
 * 
 */

export class NwesService {

/*********************************
 * 
 * ----------Api Backend---------
 * 
 *******************************/
  private base_url = environment.privateApi + "/nwes";

  headers = new HttpHeaders().set('Content-Type', 'application/json');



/***************************************************
 * 
 * -------------- Handling Erros -----------------
 * 
 **************************************************/

  //http opttion
  httpOptions = {
    headers: new HttpHeaders({
      'content-type': 'application/json'

    })
  }
  //handel api  errors 
  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      //a client-side or a neetwork error occurend .Handel it accordingly
      console.error('An Error occurend', error.error.message)

    }
    else {
      // the backend may returned an successfully response code 
      // the response body may contain clues as to what went wrong 
      console.error(`backend returned code ${error.status}, ` +
        `body was : ${error.error}`
      );
    }
    // return an observabel with a user-facing error message 
    return throwError('something bad happined , please try again later .');
  };
  
  constructor(private http: HttpClient) { }

/**************************
 * 
 * Get privacy by current Entreprise
 *
 */
  getNwesByEntreprise(): Observable<NwesDTO[]> {
    return this.http.get<NwesDTO[]>(this.base_url + '/list-nwes/' + localStorage.getItem('idEntreprise'))
  }

  
/**************************
 * 
 * Get by  id
 *
 */
  getPrivacyByid(id: number) {
    return this.http.get<NwesDTO>(this.base_url + '/' + id);
  }

  
 /**************************
  * 
 * update by Entreprise
 * 
 */
  update(request: any) {
    console.log('the request ====>',request)
    return this.http.post<NwesDTO>(`${this.base_url + '/post-nwes' }`, request);
    
  }
  
  
}
