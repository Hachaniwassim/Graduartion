import { Component, OnInit } from '@angular/core';
import { Page3DTO } from '../models/dto/page3DTO';
import { Assistanceservice } from '../_services/assistance.service';

@Component({
  selector: 'app-assitance',
  templateUrl: './assitance.component.html',
  styleUrls: ['./assitance.component.css']
})
export class AssitanceComponent implements OnInit {


  constructor( private assistanceservice : Assistanceservice) { }

  
 pageInfos !: Page3DTO;



 ngOnInit(): void {
   this.getPagesInfo();
   }


 async getPagesInfo() {
   
   await this.assistanceservice.getassistanceByEntreprise()
     .subscribe((res: any) => {
       this.pageInfos=res[0];
       console.log("==========================> test",this.pageInfos)     
       
   });
 }
}
