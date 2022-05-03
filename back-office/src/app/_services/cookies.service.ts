import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { CookieDTO } from "../models/dto/cookieDTO";

@Injectable({
  providedIn: 'root'
})

export class CookiesService {


  private base_url="http://localhost:8089/cookies";

  constructor(private http: HttpClient) {
  }

  update(request: any) {
    return this.http.post<CookieDTO>(`${this.base_url}`, request);
  }

  get() {
    return this.http.get<CookieDTO>(this.base_url);
  }

}
