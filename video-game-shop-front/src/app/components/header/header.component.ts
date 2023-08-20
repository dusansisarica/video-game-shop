import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  login(){
    this.router.navigate(['login']);
  }

  register(){
    this.router.navigate(['register']);
  }

  isLoggedIn() {
    return (localStorage.getItem("role") != null)
  }

  isUser() {
    return (localStorage.getItem("role") == "ROLE_USER")
  }

  myProfile() {
    this.router.navigate(['profile']);
  }

}
