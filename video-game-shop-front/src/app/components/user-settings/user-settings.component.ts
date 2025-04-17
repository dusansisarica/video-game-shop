import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrls: ['./user-settings.component.css']
})
export class UserSettingsComponent implements OnInit {

  users! : any[];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  getUsers() {    
  }


}
