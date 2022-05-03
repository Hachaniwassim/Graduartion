import { Component, OnInit } from '@angular/core';
import { ConnectionService } from 'ng-connection-service';  
import { Inject } from '@angular/core';

@Component({
  selector: 'app-homelist',
  templateUrl: './homelist.component.html',
  styleUrls: ['./homelist.component.css']
})
export class HomelistComponent implements OnInit {
  isConnected!:boolean;  
  noInternetConnection!:Boolean;
  constructor(private connectionService: ConnectionService ) {  
    this.connectionService.monitor().subscribe(isConnected => {  
      this.isConnected = isConnected;  
      if (this.isConnected=true) {  
        this.noInternetConnection=true;  
      }  
      else {  
        this.noInternetConnection=false;  
      }  
    })  
  }

  ngOnInit(): void {
  }

}
