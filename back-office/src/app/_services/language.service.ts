
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { languageDTO } from '../models/dto/languageDTO';

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
  createlanguage(request:any) {

    return this.http.post<languageDTO>(this.base_url + '/post-language' , request);
  }

  //get all
  getalllanguage(): Observable<languageDTO[]> {return this.http.get<languageDTO[]>(this.base_url + '/list-language/' +  localStorage.getItem('idEntreprise')).pipe(retry(2), catchError(this.handleError));
  }


  // get by id
  getByidlanguage(id: number) {
    return this.http.get<languageDTO>(this.base_url + '/' + id);


  }


  // update 
  updatelanguage(item: languageDTO) {
    return this.http.put<languageDTO>(this.base_url + '/update-language',item);
  }



  // delete 
  deletelanguage(id: number) {
    return this.http.delete<languageDTO>(this.base_url + '/' + id, this.httpOptions).pipe(retry(2), catchError(this.handleError));
  }



  //validation formulaire language
  form : FormGroup= new FormGroup({
    id: new FormControl(),
    code: new FormControl('',Validators.required),
    name : new FormControl('',[ Validators.required]),
    createdDate : new FormControl(''),
     lastModifiedDate : new FormControl(''),
    entrepriseId:new FormControl('')
});

// inialisation formulaire  language
initializeFormGroup() {
  this.form.setValue({
    id :'',
    code: '',
    name: '',
   createdDate : '',
   entrepriseId :'',
   lastModifiedDate:new Date()
  });
  
}
populateForm(language: any) {
  this.form.patchValue(_.omit(language));
}
}
