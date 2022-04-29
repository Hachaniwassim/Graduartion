import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { EntrepriseDTO } from '../models/dto/entreprisDTO';

@Injectable({
  providedIn: 'root'
})
export class EntrepriseService {

   //api backend
   private base_url="http://localhost:8089/entreprise";

 
 EntrepriseDTO ={
  //id: '',
  companyname :'',
  fiscalCode : '',
  adresse : '',
  phone: '',
  fax: '',
  email: '',
  contact : '',
  note: '',
  groupe : []
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
 
 
 // insert entreprise 
 createEntreprise(item : EntrepriseDTO):Observable<EntrepriseDTO>{
   return this.http.post<EntrepriseDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
 }
 


 //get all team data 
 getAllEntreprise():Observable<EntrepriseDTO>{
    return this.http.get<EntrepriseDTO>(this.base_url).pipe(retry(2),catchError(this.handleError));
  }
 
 
   // get team by id
   getByidEntreprise(id:number):Observable<EntrepriseDTO>{
     return this.http.get<EntrepriseDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));
 
     
   }
   // update team by Id the
   updateEntreprise(item : EntrepriseDTO){
    return this.http.put<EntrepriseDTO>(this.base_url,JSON.stringify(item),this.httpOptions).pipe(retry(2),catchError(this.handleError));
   }

     // delete groupe
     deleteEntreprise(id:number){
       return this.http.delete<EntrepriseDTO>(this.base_url + '/' +id,this.httpOptions).pipe(retry(2),catchError(this.handleError));
 
 }
    // get all groupe 
    deleteAllEntreprise(){
      return this.http.delete(this.base_url).pipe(retry(2),catchError(this.handleError));
  
    }
 
   

 //validation formulaire
   form : FormGroup = new FormGroup({
   id: new FormControl(null),
   contact: new FormControl('',Validators.required),
   companyname : new FormControl('',[ Validators.required]),
   adress: new FormControl('',[ Validators.required]),
   phone :new  FormControl('',[Validators.required]),
   email: new FormControl('',[ Validators.email,Validators.required]),
   fiscalCode: new FormControl(''),
   note : new FormControl(''),  
   fax : new FormControl('',[ Validators.required]),
   createdAt: new FormControl(''),
   updateAt: new FormControl('')
   
  
 });
 
 // inialisation formulaire 
 initializeFormGroup() {
   this.form.setValue({
     id :null,
     companyname: '',
     adresse: '',
     phone: '',
     contact: '',
     fax: true,
     createdAt: '',
     updateAt: '',
     fiscalCode:'',
      note : '',
     //groupe:['']
 
   });
 }
 populateForm(entreprise: any) {
   this.form.patchValue(_.omit(entreprise));
 }
 
 }
 
