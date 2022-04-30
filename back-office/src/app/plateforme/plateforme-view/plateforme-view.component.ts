import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { plateforomeDTO } from 'src/app/models/dto/plateformeDTO';
@Component({
  selector: 'app-plateforme-view',
  templateUrl: './plateforme-view.component.html',
  styleUrls: ['./plateforme-view.component.css']
})
export class PlateformeViewComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public plateforme:plateforomeDTO,public dialog: MatDialog,private ref: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
  }

}
