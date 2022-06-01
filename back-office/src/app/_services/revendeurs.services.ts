import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { pagesDTO } from '../models/dto/pagesDTO';
import { RevendeurDTO } from '../models/dto/revendeursDTO';

@Injectable({
  providedIn: 'root'
})
export class RevendeurService {

  //api backend
  private base_url= environment.privateApi +"/pages";
  


  constructor(private http :HttpClient) { }

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

//get by entreprise
  getpageRevendeurByEntreprise():Observable<RevendeurDTO[]>{
   return this.http.get<RevendeurDTO[]>(this.base_url + '/list-pages-revendeur/' + localStorage.getItem('idEntreprise'));
 }


   // update current page entreprise 
   updatePageRevendeur( request: any){
    return this.http.post<RevendeurDTO>(this.base_url + '/post-revendeur', request);
   }



}
