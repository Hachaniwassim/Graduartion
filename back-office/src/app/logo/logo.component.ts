import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-logo',
  templateUrl: './logo.component.html',
  styleUrls: ['./logo.component.css']
})
export class LogoComponent implements OnInit {
  private readonly IMG_MAX_SIZE = 2097152; //2MB
  imageChangeEvent: any;
  unsubscribe$ = new Subject();
  imageLoad = false;
  imageFile: any = null;
  selectedImage = false;
  croppedImage: any;
  constructor() { }

  ngOnInit(): void {
  }
}