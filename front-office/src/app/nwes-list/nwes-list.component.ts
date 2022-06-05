import { Component, OnInit } from '@angular/core';
import { NwesDTO } from '../models/dto/nwesDTO';
import { Newsservice } from '../_services/news.service';

@Component({
  selector: 'app-nwes-list',
  templateUrl: './nwes-list.component.html',
  styleUrls: ['./nwes-list.component.css']
})
export class NwesListComponent implements OnInit {

  public pageInfos !: NwesDTO;
  constructor( private nwesService: Newsservice) { }

  ngOnInit(): void {
    this.getPageInfo();
  }
  
  async getPageInfo() {
    await this.nwesService.getAllnewsByEntreprise()
      .subscribe((res: any) => {
        this.pageInfos=res[0]
        console.log("==========================> test",this.pageInfos)     
        
    });
  }

}