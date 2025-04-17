import { Component, Input, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-cards',
  templateUrl: './user-cards.component.html',
  styleUrls: ['./user-cards.component.css']
})
export class UserCardsComponent implements OnInit {

  users!: any[];
  user!: any;
  totalNumber: any;
  isVisible = false;
  admin = false;
  isVisibleManager = false;
  inputValue: string = "";
  isVisibleConfirmUnemploy = false;
  userToUnemploy: any;


  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAll();
  }

  getSearch() {
    this.getDataSearch(1);
  }

  getDataSearch(page: any) {
    if (this.isAdmin()) {
      console.log(page);
      this.userService.getAllUsersSearch(page, this.inputValue).subscribe(data => {
        this.users = data.users;
        this.totalNumber = data.totalSize;
      })
    }
    else if (this.isManager()) {
      console.log(this.inputValue)
      this.userService.getAllRoleUser(page, this.inputValue).subscribe(data => {
        this.users = data.users;
        this.totalNumber = data.totalSize;
      })
    }
  }

  getAll() {
    if (this.isAdmin()) {
      this.userService.getAllUsers(1).subscribe(data => {
        this.users = data.users;
        this.totalNumber = data.totalSize;
      })
    }
    else if (this.isManager()) {
      this.userService.getAllRoleUserNoSearch(1).subscribe(data => {
        this.users = data.users;
        this.totalNumber = data.totalSize;
      })
    }
  }

  getData(page: any) {
    if (this.isAdmin()) {
      console.log(page);
      this.userService.getAllUsers(page).subscribe(data => {
        this.users = data.users;
        this.totalNumber = data.totalSize;
      })
    }
    else if (this.isManager()) {
      this.userService.getAllRoleUserNoSearch(page).subscribe(data => {
        this.users = data.users;
        this.totalNumber = data.totalSize;
      })
    }

  }

  getUserRoleUsers(page: any) {

  }

  employUser(user: any) {
    this.user = user;
    this.isVisible = true;
    this.getData(1);
  }

  unemployUser(user: any) {
    const body = {
      userId: user.ID,
      shopId: null
    }
    this.userService.unemployUser(body).subscribe(data => {
      this.getAll();
    })
  }

  handleCancel() {
    this.isVisible = false;
  }

  handleCancelManager() {
    this.isVisibleManager = false;
  }

  handleOk() {
    this.isVisible = false;
  }

  isAdmin() {
    return (localStorage.getItem("role") == "ROLE_ADMIN")
  }

  isManager() {
    return (localStorage.getItem("role") == "ROLE_MANAGER")

  }

  showModalManager() {
    this.isVisibleManager = true;
  }

  deleteUser(id: any) {
    this.userService.deleteUser(id).subscribe(data => {
      this.getAll();
    }
    )
  }

  showConfirmUnemployModal(user: any): void {
    this.userToUnemploy = user;
    this.isVisibleConfirmUnemploy = true;
  }

  handleCancelConfirmUnemploy(): void {
    this.isVisibleConfirmUnemploy = false;
  }

  handleOkConfirmUnemploy(): void {
    this.unemployUser(this.userToUnemploy);
    this.isVisibleConfirmUnemploy = false;
  }

}
