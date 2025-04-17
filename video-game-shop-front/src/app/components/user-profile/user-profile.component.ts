import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  
  isUser() {
    return (localStorage.getItem("role") == "ROLE_USER")
  }

  isAdmin() {
    return (localStorage.getItem("role") == "ROLE_ADMIN")
  }

  isStaff() {
    return (localStorage.getItem("role") == "ROLE_STAFF")
  }

  isManager() {
    return (localStorage.getItem("role") == "ROLE_MANAGER")
  }


}
