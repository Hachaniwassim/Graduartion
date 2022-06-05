import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class SetGroupInterceptor implements HttpInterceptor{

  constructor(
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url.startsWith('/api')) {
      const request = req.clone({
        setHeaders: {
          'group-id': environment.groupId,
          'entreprise_id': '2',/*environment.enterpriseId*/
        }
      });
      return next.handle(request);
    }
    return next.handle(req);
  }
}