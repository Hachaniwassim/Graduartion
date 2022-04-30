import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { productsDTO } from 'src/app/models/dto/productsDTO';

@Component({
  selector: 'app-posproductlist-view',
  templateUrl: './posproductlist-view.component.html',
  styleUrls: ['./posproductlist-view.component.css']
})
export class PosproductlistViewComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public company:productsDTO,public dialog: MatDialog,private ref: ChangeDetectorRef
  ) { }
  ngOnInit(): void {
  }

}
