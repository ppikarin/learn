import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { USERS } from './mock-users';
import { User } from './User';
import { UserItemComponent } from './user-item.component';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  ngOnInit(): void {
  }

  userListTitle: String = 'User list component';

  users: User[] = USERS;

}
