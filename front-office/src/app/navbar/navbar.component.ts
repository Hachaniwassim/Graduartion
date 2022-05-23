import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Location}from '@angular/common';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router : Router, public _location: Location) { }

  ngOnInit(): void {
  }
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
  

}
