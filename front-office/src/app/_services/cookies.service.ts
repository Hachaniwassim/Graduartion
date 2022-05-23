import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { CookieDTO } from "../models/dto/cookieDTO";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: 'root'
})

export class CookiesService {


  private base_url= environment.publicApi +'/cookies';

  constructor(private http: HttpClient) {
  }


  getCurrentEnterpriseCookies() {
    return this.http.get<CookieDTO[]>(`${this.base_url}`);
  }

}
