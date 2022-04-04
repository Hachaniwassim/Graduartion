import { Component, OnInit } from '@angular/core';
import { users } from './user';
import { user } from './user.model';
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  public usersList: user[] = users;


  constructor() { }

  ngOnInit(): void {
  }

}
