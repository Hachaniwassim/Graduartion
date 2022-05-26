import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private tokenStorage: TokenStorageService) { }
  isLoggedIn = false;
  isLoginFailed = false;

  ngOnInit() {
   /* if (!localStorage.getItem('reload')) {
      setInterval(() => {
        //replaced function() by ()=>
        window.location.reload();
      }, 1000);
    } else {
      localStorage.setItem('reload','ddffff');
    }*/
  }
 
  reloadPage() {
    setTimeout(()=>{
      window.location.reload();
    }, 1000);
}
}
