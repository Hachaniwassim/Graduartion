import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { productsDTO } from '../models/dto/productsDTO';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  //api backend
  private base_url= environment.privateApi +"/product";
  
  headers = new HttpHeaders().set('Content-Type', 'application/json');


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


// create product
createproduct(request:any){

  return this.http.post<productsDTO>(`${this.base_url + '/post-product' }`, request);
}


//get all 
getAllProductsByEntreprise():any{
    return this.http.get<any>(this.base_url + '/list-products/' + localStorage.getItem('idEntreprise'));
  }


// get by id
getByidProducts(id:number):Observable<productsDTO>{
  return this.http.get<productsDTO>(this.base_url + '/' +id).pipe(retry(2),catchError(this.handleError));

}

  update(request: any) {
  console.log('the request ====>',request)
  return this.http.put<productsDTO>(`${this.base_url + '/update-product' }`, request);
  
}


  // delete 
  deleterPoducts(id:number){
    return this.http.delete<productsDTO>(this.base_url + '/' +id);

}

//validation formulaire
    form : FormGroup= new FormGroup({
    id: new FormControl(null),
    title: new FormControl('',Validators.required),
    description : new FormControl('',[ Validators.required]),
    caracteristique : new FormControl('',[ Validators.required]),
    name : new FormControl('',[ Validators.required]),
    image : new FormControl(''),
    requirements : new FormControl('',Validators.required),
    createdDate: new FormControl(''),
    lastModifiedDate : new FormControl(''),
    entrepriseId : new FormControl(''),
    categorieId : new FormControl(''),
    tagId: new FormControl(''),
    slug: new FormControl('',Validators.required)

 
});

// inialisation formulaire 
initializeFormGroup() {
  this.form.setValue({
    id :null,
    title: '',
    caracteristique: '',
    note: '',
    image: '',
    description: '',
    requirements:'', 
    entrepriseId:null,
    categorieId:null,
    tagId:null,
    createdDate:'',
    lastModifiedDate:'',
    slug:''

  });
}
populateForm(product: any) {
  this.form.patchValue(_.omit(product));
}
getTagsByEntreprise():any{
  return this.http.get<any>(environment.privateApi + '/tags/list-tags/' + localStorage.getItem('idEntreprise'));
}

getCategorieByEntreprise():any{
  return this.http.get<any>(environment.privateApi + '/category/list-category/' + localStorage.getItem('idEntreprise'));
}
//for add an product
assignTagsToAnProduct(productId:any,tagId:any):any{
  return this.http.get<any>(environment.privateApi + '/product/assign-tags/' +productId+"/"+tagId);
}

//upload file product
uploadProductImage(nameImage:any,file:any):any{

  
  const formData: FormData = new FormData();
  formData.append('file', file);
  return this.http.post<any>(environment.privateApi + '/upload/PRODUCT/' +nameImage+"/"+localStorage.getItem('idEntreprise'),formData);
}

addProduct(bodyReques:any):any{
  
  return this.http.post<any>(environment.privateApi + '/product/post-product',bodyReques);
}
}