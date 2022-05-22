import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { languageDTO } from '../models/dto/languageDTO';
import { Language } from '../models/enum/language.enum';

@Injectable({
  providedIn: 'root'
})
export class Languageservice {

  //api backend
  private base_url= environment.privateApi +"/languages";
  
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


  // insert 
  createlanguage(item: any): Observable<languageDTO> {
  
    return this.http.post<languageDTO>(this.base_url, JSON.stringify(item), this.httpOptions).pipe(retry(2), catchError(this.handleError));
  }

  //get all team data 
  getalllanguage(): Observable<languageDTO[]> {
    return this.http.get<languageDTO[]>(this.base_url).pipe(retry(2), catchError(this.handleError));
  }


  // get team by id
  getByidlanguage(id: number): Observable<languageDTO> {
    return this.http.get<languageDTO>(this.base_url + '/' + id).pipe(retry(2), catchError(this.handleError));


  }


  // update team by Id the
  updatelanguage(item: languageDTO) {
    return this.http.put<languageDTO>(this.base_url, JSON.stringify(item), this.httpOptions).pipe(retry(2), catchError(this.handleError));
  }

  // delete groupe
  deletelanguage(id: number) {
    return this.http.delete<languageDTO>(this.base_url + '/' + id, this.httpOptions).pipe(retry(2), catchError(this.handleError));

  }
  // get all groupe 
  deleteAlllanguage() {
    return this.http.delete(this.base_url).pipe(retry(2), catchError(this.handleError));

  }


  //find by active
  findByActivelanguage(active: any) {
    return this.http.get(`${this.base_url}?active${active}`);
  }



  //find by confirmed
  findByConfirmelanguage(confirme: any) {
    return this.http.get(`${this.base_url}?confirme${confirme}`);
  }


//validation formulaire language
  form : FormGroup= new FormGroup({
    id: new FormControl(null),
    lang: new FormControl('',Validators.required),
    name : new FormControl('',[ Validators.required]),
    image : new FormControl('',[ Validators.required]),
    active: new FormControl('', [Validators.required]),
});

// inialisation formulaire  language
initializeFormGroup() {
  this.form.setValue({
    id :null,
    lang: null,
    name: null,
   // image: null,
    active: true,
  });
  
}
populateForm(language: any) {
  this.form.patchValue(_.omit(language));
}
   //update groupe  by status
   updatelanguageByStatus(id : number,item : Language){
    return this.http.put<any>(this.base_url + '/toggle-status/' + id,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
     }
   
}
