import { Component, OnInit } from '@angular/core';
import { configuration } from './configurations.model';
import { configurations } from './configurations';
@Component({
  selector: 'app-configurations',
  templateUrl: './configurations.component.html',
  styleUrls: ['./configurations.component.css']
})
export class ConfigurationsComponent implements OnInit {

  public cofigrationsList: configuration[] = configurations;

  constructor() { }

  ngOnInit(): void {
  }

}
