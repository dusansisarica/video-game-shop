import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  imports: [MatButtonModule, MatIconModule, MatDividerModule],
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.router.navigate(['login']);
  }

  register() {
    this.router.navigate(['register']);
  }

  isLoggedIn() {
    return (localStorage.getItem("role") != null)
  }

  isUser() {
    return (localStorage.getItem("role") == "ROLE_USER")
  }

  isAdmin() {
    return (localStorage.getItem("role") == "ROLE_ADMIN")
  }

  myProfile() {
    this.router.navigate(['profile/details']);
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['']);
  }

  myCart() {
    this.router.navigate(['cart']);
  }

  shop() {
    this.router.navigate(['shop']);
  }

}
