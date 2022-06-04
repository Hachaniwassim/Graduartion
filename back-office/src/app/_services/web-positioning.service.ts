import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PagesTypes, WebPositioning} from "../models/seo/web-positioning";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WebPositioningService {
  private readonly API = environment.privateApi + '/web-positioning';

  constructor(
    private http: HttpClient
  ) { }

  update(request: any) {
    return this.http.post<void>(this.API + "/update-web-positioning", request);
  }

  getCurrentEnterprisePageInfo(page: PagesTypes) {
    return this.http.get<WebPositioning>(this.API + '/' + page + '/'+ localStorage.getItem('idEntreprise'));
  }


}
