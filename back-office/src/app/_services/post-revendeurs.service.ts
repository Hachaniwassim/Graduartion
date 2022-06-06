import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as _ from 'lodash';
import { Observable, throwError} from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { PostRevendeurDTO } from '../models/dto/post-revebdeurDTO';

@Injectable({
  providedIn: 'root'
})
/*****
 * @author Tarchoun Abir
 * 
 */
export class PostRevendeurService {

  //api backend
  private base_url=environment.privateApi + '/form-revendeurs';
  
  constructor(private http :HttpClient) { }




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

 
  //get all by entreprise
getByEntreprise():any{
  return this.http.get<PostRevendeurDTO[]>(this.base_url + '/list-form-revendeurs/' + localStorage.getItem('idEntreprise'));
 }

 // get by id
 getByid(id:number):Observable<PostRevendeurDTO>{
   return this.http.get<PostRevendeurDTO>(this.base_url + '/' +id);
 
 }
 create(item :any):any{
  return this.http.post<PostRevendeurDTO>(this.base_url +  '/post-form',item);
}
 
  //update by status
  updateRevendeurByStatus(id: number, item: string) {
    return this.http
      .put<any>(
        this.base_url + '/toggle-status/' + id,
        JSON.stringify(item),
        this.httpOptions
      )
      .pipe(retry(2), catchError(this.handleError));
  }
   
  deleteRevendeur(id: number) {
    return this.http.delete<PostRevendeurDTO>(`${this.base_url}` + '/' + id)

  }
 

}
