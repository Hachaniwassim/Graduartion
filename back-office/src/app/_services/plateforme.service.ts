import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { plateforomeDTO } from '../models/dto/plateformeDTO';

@Injectable({
  providedIn: 'root'
})
export class PlateformeService {

  //api backend
  private base_url=environment.privateApi + '/platefome';
  

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


// insert 
createplateforme(item : plateforomeDTO):Observable<plateforomeDTO>{
  return this.http.post<plateforomeDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all data 
getAllplateformes():Observable<plateforomeDTO[]>{
   return this.http.get<plateforomeDTO[]>(this.base_url).pipe(retry(2),catchError(this.handleError));
 }


  // get by id
  getByidplateforme(id:number):Observable<plateforomeDTO>{
    return this.http.get<plateforomeDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }

   // update by Id the
   updateplateforme(item : plateforomeDTO){
    return this.http.put<plateforomeDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
   }

    // delete
    deleteplateforme(id:number){
      return this.http.delete<plateforomeDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire
  form : FormGroup= new FormGroup({
    id: new FormControl(null),
    image: new FormControl(''),
    email : new FormControl('',[ Validators.required]),
    phone : new FormControl('',[ Validators.required]),
    adresse : new FormControl('',[ Validators.required]),
    published : new FormControl('',[ Validators.required]),

 
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
    id :null,
    image: null,
    email: null,
    phone: null,
    adresse: null,
    published: null,
  });
}

// get value of formulaire mode update
populateForm(plateforme: any) {
  this.form.patchValue(_.omit(plateforme));
}

}
