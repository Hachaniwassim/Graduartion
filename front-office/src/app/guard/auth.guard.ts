import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
@Injectable({
  providedIn: 'root'
})

/**
 * 
 * @author Tarchoun Abir
 *
 **/

export class AuthGuard implements CanActivate {


  constructor(
    public authService: AuthService,
    public router: Router, private tokenStorageService: TokenStorageService
  ){ }
  isLoggedIn  = !!this.tokenStorageService.getToken() 
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if(this.isLoggedIn == !true ) {
      this.router.navigate(['/']);
    }
      return true;
    }
  
  }

