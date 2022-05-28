import { Component, OnInit } from '@angular/core';
import { UploadFiles } from 'src/app/_services/uploadFiles';

@Component({
  selector: 'app-homeprimaryslide',
  templateUrl: './homeprimaryslide.component.html',
  styleUrls: ['./homeprimaryslide.component.css']
})
export class HomeprimaryslideComponent implements OnInit {
  file:any;
  constructor(private uploadFiles:UploadFiles) { }

  ngOnInit(): void {
  }

  onSelectFile(file: any){
    //console.log('file =======================>',file.target.files[0]);
    this.file = file.target.files[0]
   
    
  }
  onUploadFile(){
    this.uploadFiles.uploadFile(this.file).subscribe((res:any)=>{
      console.log('hey i am responce of upload image =>',res)
    },
    
    (err:any)=>{console.log('hey i am the error  =>',err)})  
    //window.location.reload();
  }
 
}