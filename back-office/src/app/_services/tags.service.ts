import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators'
import { TagsDTO } from '../models/dto/TagsDTO';

@Injectable({
  providedIn: 'root'
})
export class TagsSerice {

  //api backend
  private base_url="http://localhost:8089/tags";
  

 TagsDTO ={
    description:'', 
    createdDate :'',
    lastModifiedDate:'',
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
createtags(item :TagsDTO):Observable<TagsDTO>{
  return this.http.post<TagsDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
}

//get all data 
getAlltags():Observable<TagsDTO>{
   return this.http.get<TagsDTO>(this.base_url).pipe(retry(2),catchError(this.handleError));
 }


  // get tags by id
  getByidTags(id:number):Observable<TagsDTO>{
    return this.http.get<TagsDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

  }

   // update tags by Id the
   updateTags(item :TagsDTO){
    return this.http.put<TagsDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
   }

    // delete cars
    deleteTags(id:number){
      return this.http.delete<TagsDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));

}

//validation formulaire
  form : FormGroup= new FormGroup({
    id: new FormControl(null),
    description: new FormControl('',Validators.required),
 
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
    id :null,
    description: '',
   

  });
}
populateForm(tags: any) {
  this.form.patchValue(_.omit(tags));
}
}
