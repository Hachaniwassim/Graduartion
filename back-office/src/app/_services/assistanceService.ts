
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Page2DTO } from '../models/dto/page2DTO';

@Injectable({
  providedIn: 'root'
})

/**
 * 
 * @author Tarchoun Abir
 * 
 */

export class AssistanceService {

  /*********************************
   * 
   *    ----Api Backend----
   * 
   *******************************/

  private base_url = environment.privateApi + '/pages2';

  /***************************************
   * 
   * ------ Handling Erros -------
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

  getPagesByCurrentEntreprise(): Observable<Page2DTO[]> {
    return this.http.get<Page2DTO[]>(this.base_url + '/list-page2/' +  localStorage.getItem('idEntreprise')).pipe(retry(2), catchError(this.handleError));
  }

  /****************************
   * 
   * Get page1 by Entreprise id
   *
   ****************************/

  getPage2Byid(id: number): Observable<Page2DTO> {
    return this.http.get<Page2DTO>(this.base_url + '/' + id).pipe(retry(2), catchError(this.handleError));

  }

  /*************************
   *
   * update  by Entreprise
   * 
   *************************/
  updateAssistance(request: any) {
    return this.http.post<Page2DTO>(`${this.base_url+ "/post-assitance"}`, request);
  }


}
