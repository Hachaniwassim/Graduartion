
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Page3DTO } from '../models/dto/page3DTO';

@Injectable({
  providedIn: 'root'
})

/**
 * 
 * @author Tarchoun Abir
 * 
 */

export class HomeService {

  /*********************************
   * 
   *    -----Api Backend-----
   * 
   *******************************/
  private base_url = environment.privateApi + '/pages3';

  headers = new HttpHeaders().set('Content-Type', 'application/json');



  /**************************************
   * 
   *   ------- Handling Erros ---------
   * 
   **************************************/

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

  /********************************
   * 
   * Get page by current Entreprise
   *
   *******************************/

  getPagesByCurrentEntreprise(): Observable<Page3DTO[]> {
    return this.http.get<Page3DTO[]>(this.base_url + '/list-page3/' +  localStorage.getItem('idEntreprise')).pipe(retry(2), catchError(this.handleError));
  }

  /****************************
   * 
   * Get page by Entreprise id
   *
   ***************************/

  getPage1Byid(id: number): Observable<Page3DTO> {
    return this.http.get<Page3DTO>(this.base_url + '/' + id).pipe(retry(2), catchError(this.handleError));

  }

  /**************************
   * 
  * update  by Entreprise
  * 
  **************************/
  update(request: any) {
    return this.http.post<Page3DTO>(`${this.base_url + "/post-home-text"}`, request);
  }


}
