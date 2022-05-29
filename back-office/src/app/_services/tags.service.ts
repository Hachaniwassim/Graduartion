import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators'
import { environment } from 'src/environments/environment';
import { TagsDTO } from '../models/dto/TagsDTO';

@Injectable({
  providedIn: 'root'
})
export class TagsSerice {

  //api backend
  private base_url= environment.privateApi + "/tags";
  
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


// insert 
createtags(request: any){
  return this.http.post<TagsDTO>(this.base_url,request)
}

//get all 
getAlltags():Observable<TagsDTO[]>{
   return this.http.get<TagsDTO[]>(this.base_url + '/list-tags/' + localStorage.getItem('idEntreprise'));
}

  // get tags by id
  getByidTags(id:number):Observable<TagsDTO>{
    return this.http.get<TagsDTO>(this.base_url + '/' +id)

  }

   // update tags by Id the
   updateTags(item :TagsDTO){
    return this.http.put<TagsDTO>(this.base_url,item,this.httpOptions);
   }

    // delete cars
    deleteTags(id:number){
      return this.http.delete<TagsDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2), catchError(this.handleError));

}

//validation formulaire
  form : FormGroup= new FormGroup({
    id: new FormControl(null),//identreprise
    entrepriseId: new FormControl(null),
    description: new FormControl('',Validators.required),
    createdDate:new FormControl(''),
    lastModifiedDate:new FormControl('')
    
 
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
    id :null,
    entrepriseId :null,
    description: '',
    lastModifiedDate:new Date(),
    createdDate:''
   

  });
}
populateForm(tags: any) {
  this.form.patchValue(_.omit(tags));
}
}
