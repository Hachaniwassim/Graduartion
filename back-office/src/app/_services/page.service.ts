import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { pagesDTO } from '../models/dto/pageDTO';

@Injectable({
  providedIn: 'root'
})
export class PageService {

  //api backend
  private base_url="http://localhost:8089/pages";
  

 PagesDTO ={
  published:'', 
  pagetype: '',
  title: '',
  description: '',

  }
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
createPages(item : pagesDTO):Observable<pagesDTO>{
  return this.http.post<pagesDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all page data 
getAllPages():Observable<pagesDTO>{
   return this.http.get<pagesDTO>(this.base_url).pipe(retry(2),catchError(this.handleError));
 }


  // get page by id
  getByidPages(id:number):Observable<pagesDTO>{
    return this.http.get<pagesDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }

   // update page by Id the
   updatePages(item : pagesDTO){
    return this.http.put<pagesDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
   }

    // delete pages
    deletePages(id:number){
      return this.http.delete<pagesDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire
   form : FormGroup= new FormGroup({
    id: new FormControl(null),
    published: new FormControl('',Validators.required),
    pagetype : new FormControl('',[ Validators.required]),
    title : new FormControl('',[ Validators.required]),
    description : new FormControl('',[ Validators.required]),

 
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
    id :null,
    published: null,
    pagetype: null,
    title: null,
    description: null,
  });
}
populateForm(pages: any) {
  this.form.patchValue(_.omit(pages));
}
}
