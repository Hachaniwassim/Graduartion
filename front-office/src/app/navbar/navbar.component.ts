import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Location}from '@angular/common';
import { TokenStorageService } from '../_services/token-storage.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  showUserBoard = false ;
  entrepriseSelected = false;
  username?: string;


  constructor(private tokenStorageService: TokenStorageService,private router : Router, public _location: Location) { }


    //refrech 
    refresh(): void {
      this.router.navigateByUrl("/refresh", { skipLocationChange: true }).then(() => {
        console.log(decodeURI(this._location.path()));
        this.router.navigate([decodeURI(this._location.path())]);
      });
    }

    reloadPage() {
      setTimeout(()=>{
        window.location.reload();
      }, 1000);
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      //this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      //this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');
      this.showUserBoard = this.roles.includes('ROLE_USER')
      this.username = user.username;
      this.entrepriseSelected = localStorage.getItem('idEntreprise') ? true:false
    }
   
  }
  logout(): void {
   this.tokenStorageService.signOut();
    window.location.reload();
    this.refresh();
  }

  

}
