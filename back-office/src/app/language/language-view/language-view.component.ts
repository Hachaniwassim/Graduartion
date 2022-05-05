import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { languageDTO } from 'src/app/models/dto/languageDTO';
@Component({
  selector: 'app-language-view',
  templateUrl: './language-view.component.html',
  styleUrls: ['./language-view.component.css']
})
export class LanguageViewComponent implements OnInit {


  constructor(@Inject(MAT_DIALOG_DATA) public language:languageDTO,public dialog: MatDialog,private ref: ChangeDetectorRef
  ) { }
  ngOnInit(): void {
  }

}
