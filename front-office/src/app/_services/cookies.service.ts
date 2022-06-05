import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { CookieDTO } from "../models/dto/cookieDTO";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: 'root'
})

export class CookiesService {


  private base_url= environment.publicApi +'/cookies-public';

  constructor(private http: HttpClient) {
  }


  getCurrentEnterpriseCookies():any {
    return this.http.get<CookieDTO[]>(`${this.base_url + '/list-cookies/' + environment.enterpriseId}`);
  }

}
