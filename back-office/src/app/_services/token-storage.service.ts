import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() { }

  //session clear
  signOut(): void {
    window.sessionStorage.clear();
  }

  //saveToken information
  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  //get token information
  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  //save user session information 
  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  // get currentUser session  information
  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }


  //set session to clear later 

  set(itemName: string, item: any) {
    localStorage.setItem(itemName, JSON.stringify(item));
  }
  // clear session  of currentUser
  clear(itemName: string) {
    localStorage.removeItem(itemName);
  }
}
