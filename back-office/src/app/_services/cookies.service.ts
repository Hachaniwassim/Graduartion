import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import { CookieDTO } from "../models/dto/cookieDTO";
import { environment } from "src/environments/environment";
import { Observable, throwError } from "rxjs";
import { catchError, retry } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
/**
 * 
 * @author Tarchoun Abir
 * 
 */

export class CookiesService {

  /**************************
   * 
   * ---- Api Backend------
   * 
   *************************/
  private base_url = environment.privateApi + '/cookies';

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

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




   /**************************
   * 
   * Get Privacy By EntrepriseId 
   * 
   */
  getCookiesByEntrepriseId() : Observable<CookieDTO[]> {
    return this.http.get<CookieDTO[]>(this.base_url + '/list-cookies/' +  localStorage.getItem('idEntreprise')).pipe(retry(2), catchError(this.handleError));

  }

  /**************************
   * 
   * UpdateBy Entreprise
   * 
   */
  update(request: any) {
    return this.http.post<CookieDTO>(`${this.base_url + "/post-cookies"}`, request);
  }

}
