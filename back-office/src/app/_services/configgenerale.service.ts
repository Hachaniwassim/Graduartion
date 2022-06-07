import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { configgeneraleDTO } from '../models/dto/configgeneraleDTO';

@Injectable({
  providedIn: 'root'
})
export class ConfigGeneraleService {

  //api backend
  private base_url= environment.privateApi +'/config';
  

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
getConfigByEntreprise(){
   return this.http.get<configgeneraleDTO[]>(this.base_url + '/config-entreprise/' + localStorage.getItem('idEntreprise'));
 }



   // update by entreprise
   update( request : any){
    return this.http.put<configgeneraleDTO>(this.base_url + '/put-config',request)
   }


}
