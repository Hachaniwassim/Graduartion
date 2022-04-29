import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { GroupeDTO } from '../models/dto/groupeDTO';

@Injectable({
  providedIn: 'root'
})
export class GroupeService {
   //api backend
   private base_url="http://localhost:8089/groupe";
  
   headers = new HttpHeaders().set('Content-Type', 'application/json');
 
  GroupeDTO ={
    id: '',
    name: '',
    description: '',
    active: true,
    createdAt: '',
    updateAt: '',
    confirmed: true,
    deleted: false,
    companyBusiness:[]
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
 create(item : GroupeDTO):Observable<GroupeDTO>{
   return this.http.post<GroupeDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
 }
 
 //get all team data 
 all():Observable<GroupeDTO>{
    return this.http.get<GroupeDTO>(this.base_url).pipe(retry(2),catchError(this.handleError));
  }
 
 
   // get team by id
   getByid(id:number):Observable<GroupeDTO>{
     return this.http.get<GroupeDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));
 
     
   }
 
    // update groupe 
    update(id : number,data: GroupeDTO, ){
     return this.http.put<GroupeDTO>(`${this.base_url}/${id}`,JSON.stringify(data),this.httpOptions).pipe(retry(2),catchError(this.handleError));
    }

  
 
     // delete groupe
     delete(id:number){
       return this.http.delete<GroupeDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));
 
 }
    // get all groupe 
    deleteAll(){
      return this.http.delete(this.base_url).pipe(retry(2),catchError(this.handleError));
  
    }
 
   
//find by active
    findByActive(active:any) {
      return this.http.get(`${this.base_url}?active${active}`);
    }



  //find by confirmed
    findByConfirmed(confirme: any) {
      return this.http.get(`${this.base_url}?confirme${confirme}`);
    }
   

 //validation formulaire
   form : FormGroup= new FormGroup({
   id: new FormControl(null),
   description: new FormControl('',Validators.required),
   name : new FormControl('',[ Validators.required]),
   confirmed: new FormControl(''),
   active :new  FormControl('',[ Validators.required]),
   deleted: new FormControl(''),
   companyBusiness: new FormControl('')
   
  
 });
 
 // inialisation formulaire 
 initializeFormGroup() {
   this.form.setValue({
     id :null,
     name: '',
     description: '',
     active: true,
     createdAt: '',
     updateAt: '',
     confirmed: true,
     deleted: false,
     companyBusiness:['']
 
   });
 }
 populateForm(groupe: any) {
   this.form.patchValue(_.omit(groupe));
 }

 // update groupe  by status
 updateG(item : GroupeDTO,id : any){
  return this.http.put<GroupeDTO>(this.base_url + '/' +id,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
 }

 
 }
 
