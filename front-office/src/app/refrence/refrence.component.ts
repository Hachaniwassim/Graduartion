import { Component, OnInit } from '@angular/core';
import { postDTO } from '../models/dto/postDTO';
import { PostService } from '../_services/post.service';

@Component({
  selector: 'app-refrence',
  templateUrl: './refrence.component.html',
  styleUrls: ['./refrence.component.css']
})
export class RefrenceComponent implements OnInit {

  pageInfos !: postDTO;

  constructor( private postService : PostService) { }

  ngOnInit(): void {
    this.getrefrence();
   
  }


  async getrefrence() {
    await this.postService.getAllByEntreprise()
      .subscribe((res: any) => {
        this.pageInfos=res[0];
        console.log("==========================> test",this.pageInfos)     
        
    });
  }

 
}


