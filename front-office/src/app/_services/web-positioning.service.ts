import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PagesTypes, WebPositioning} from "../models/seo/web-positioning";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WebPositioningService {
  private readonly API = environment.publicApi + '/web-positioning';

  constructor(
    private http: HttpClient
  ) { }

  

  getCurrentEnterprisePageInfo(page: PagesTypes) {
    return this.http.get<WebPositioning>(this.API + '/' + page);
  }


}
