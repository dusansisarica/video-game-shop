import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  public user : any;
  public name = '';
  profileForm!: FormGroup;
  
  constructor(private userService: UserService, private fb: FormBuilder) { 
     this.getLoggedInUser();
  }

  ngOnInit(): void {
    this.getLoggedInUser();
    // if (this.user) {
    //   this.profileForm = this.fb.group({
    //     name: [''],      // Can be empty
    //     surname: [''],   // Can be empty
    //     address: [''],   // Can be empty
    //     city: [''],      // Can be empty
    //     country: ['']    // Can be empty
    //   });

    //   this.profileForm.patchValue({
    //     name: this.user.name,
    //     surname: this.user.surname,
    //     address: this.user.address.address,
    //     city: this.user.address.city,
    //     country: this.user.address.country
    //   });
    // }
    
  }

  getLoggedInUser() {
    this.userService.getLoggedInUser().subscribe(data => {
      this.user = data;
      console.log(this.user.name);
      this.profileForm = this.fb.group({
        name: [''],
        surname: [''],
        address: [''],
        city: [''],
        country: ['']
      });
  
      this.profileForm.patchValue({
        name: this.user.name,
        surname: this.user.surname,
        address: this.user.address.address,
        city: this.user.address.city,
        country: this.user.address.country
      });
    });
    
  }

  onSubmit() {
    const body = {
      email : this.user.email,
      name : this.profileForm.get('name')?.value,
      surname : this.profileForm.get('surname')?.value,
      address : {
        address : this.profileForm.get('address')?.value,
        city : this.profileForm.get('city')?.value,
        country : this.profileForm.get('country')?.value,
      },
      jwt : ""
    }
    this.userService.changeUserDetails(body).subscribe();
  //  this.getLoggedInUser();
  }

}
