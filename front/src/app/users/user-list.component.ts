import { HttpClient } from '@angular/common/http';
import { KeyedRead } from '@angular/compiler';
import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import { map } from 'rxjs';
import { USERS } from './mock-users';
import { User } from './User';
import { UserItemComponent } from './user-item.component';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

 constructor(private http: HttpClient) {
 }


  userListTitle: String = 'User list component';

  // users: User[] = USERS;
  users: User[] = [];

  ngOnInit(): void {
    this.fetchUsers();
  }


  onUserSubmit(user: User) {
    const obs = this.http.post("http://localhost:8080/userAccounts", user);
    obs.subscribe((response) => {
      console.log(response);
    });
  }

  fetchUsers() {
    const obs = this.http.get("http://localhost:8080/userAccounts");
    obs
    .pipe(map((response) => {
      const accs = response["_embedded"]["userAccounts"];
      // console.log(accs);
      for (const key in accs) {
        // console.log(key);
        this.users.push(accs[key])
      }
    }))
    .subscribe((response) => {
      console.log(this.users);
    });
  }



}
