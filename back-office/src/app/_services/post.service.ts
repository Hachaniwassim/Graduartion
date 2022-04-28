import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { postDTO } from '../models/dto/postDTO';

@Injectable({
  providedIn: 'root'
})
export class Postservice {

  //api backend
  private base_url="http://localhost:8089/";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');

 CompanyBusinessDTO ={
  image:'', 
  tagline: '',
  postTranslations: '',
  title: '',
  description: '',
  content: '',
  slug: '',
  subtitle: '',


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
create(item : postDTO):Observable<postDTO>{
  return this.http.post<postDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all post data 
all():Observable<postDTO>{
   return this.http.get<postDTO>(this.base_url).pipe(retry(2),catchError(this.handleError));
 }


  // get post by id
  getByid(id:number):Observable<postDTO>{
    return this.http.get<postDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }

   // update post by Id the
   update(item : postDTO){
    return this.http.put<postDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
   }

    // delete posts
    delete(id:number){
      return this.http.delete<postDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire
  form : FormGroup= new FormGroup({
    id: new FormControl(null),
    image: new FormControl('',Validators.required),
    tagline : new FormControl('',[ Validators.required]),
    postTranslations : new FormControl('',[ Validators.required]),
    title : new FormControl('',[ Validators.required]),
    description : new FormControl('',[ Validators.required]),
    content : new FormControl('',[ Validators.required]),
    slug : new FormControl('',[ Validators.required]),
    subtitle : new FormControl('',[ Validators.required]),

 
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
    id :null,
    image: null,
    tagline: null,
    postTranslations: null,
    title: null,
    description: null,
    content: null,
    slug: null,
    subtitle: null,

  });
}
populateForm(company: any) {
  this.form.patchValue(_.omit(company));
}
}
