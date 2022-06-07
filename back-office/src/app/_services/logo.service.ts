import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { categoryDTO } from '../models/dto/categoryDTO';

@Injectable({
  providedIn: 'root'
})

/*****
 * 
 * 
 * @author Tarchoun Abir
 * 
 */
export class Logoservice {

  //api backend
  private base_url= environment.privateApi + '/logo';
  


idIfEdit:any=null;
  constructor(private http :HttpClient) { }

  //http opttion
  httpOptions={ 
    headers:new HttpHeaders({
      'content-type':'application/json'

    })
  }


// insert 
createCategory(request:any){
  return this.http.post<any>(`${this.base_url + '/post-logo' }`, request);
}

//get all 
getallCategorieByEntreprise():Observable<categoryDTO[]>{
   return this.http.get<categoryDTO[]>(this.base_url + '/list-logo/' + localStorage.getItem('idEntreprise'));
 }

 // delete
    deleteCategory(id:number){
      return this.http.delete<categoryDTO>(this.base_url + '/' +id);

}

//upload image

uploadLogoImage(nameImage:any,file:any):any{
  
  const formData: FormData = new FormData();
  formData.append('file', file);
  return this.http.post<any>(environment.privateApi + '/upload/LOGO/' +nameImage+"/"+localStorage.getItem('idEntreprise'),formData);
}


//validation formulaire
    form : FormGroup= new FormGroup({
    id: new FormControl(),
    title : new FormControl('',[ Validators.required]),
    createdDate : new FormControl(''),
    lastModifiedDate : new FormControl(''),
    
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
     id :'',
    title: '',
    createdDate: '',
    enterpriseId :localStorage.getItem('idEntreprise'),
    lastModifiedDate:new Date()
   
  });
}
}
