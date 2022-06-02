import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { postDTO } from '../models/dto/postDTO';

@Injectable({
  providedIn: 'root'
})
/****
 * 
 * @author Tarchoun Abir
 * 
 */
export class PostClientService {

  //api backend
  private base_url= environment.privateApi + "/logo-clients";
  

  constructor(private http :HttpClient, private datePipe: DatePipe) { }

  //http opttion
  httpOptions={ 
    headers:new HttpHeaders({
      'content-type':'application/json'

    })
  }
  //handel api  errors 
  handleError(error: HttpErrorResponse){
    if( error.error instanceof ErrorEvent){
    //a client-side or a neetwork error occurend .Handel it accordingly
    console.error('An Error occurend' , error.error.message)

  }
  else{
    // the backend may returned an successfully response code 
    // the response body may contain clues as to what went wrong 
    console.error(`backend returned code ${error.status}, ` +
    `body was : ${ error.error}`
    );}
   // return an observabel with a user-facing error message 
  return throwError( 'something bad happined , please try again later .');
};



//get all post data 
getallLogoClientByEntreprise():Observable<postDTO[]>{
   return this.http.get<postDTO[]>(this.base_url + '/list-post/' + localStorage.getItem('idEntreprise'));
 }



  // get post by id
  getByid(id:number):Observable<postDTO>{
    return this.http.get<postDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }

  /**************************
  * 
 * update privacy by Entreprise
 * 
 */
   update(request: any) {
    console.log('the request ====>',request)
    return this.http.post<postDTO>(`${this.base_url + '/post-logo-clients' }`, request);

   }
    
}
