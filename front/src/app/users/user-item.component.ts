import { Component, OnInit, Input } from '@angular/core';
import { User } from '../users/User';

@Component({
  selector: 'app-user-item',
  templateUrl: './user-item.component.html',
  styleUrls: ['./user-item.component.css']
})
export class UserItemComponent implements OnInit{

  ngOnInit(): void {
  }

  @Input() userItem?: User;


}
