import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationForm: FormGroup;

  constructor(private fb: FormBuilder, private authService : AuthService, private router : Router) { 
    this.registrationForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      passwordConfirm: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      passwordConfirm: ['', Validators.required]
    });
  }

  onRegistrationSubmit() {
    if (this.registrationForm.valid) {
      const emailValue = this.registrationForm.get('email')?.value;
      const passwordFirst = this.registrationForm.get('password')?.value;
      const passwordSecond = this.registrationForm.get('passwordConfirm')?.value;

      const body = {
        email : emailValue,
        passwordFirst : passwordFirst,
        passwordSecond : passwordSecond
      }
      this.authService.register(body).subscribe(data => {
        this.router.navigate(['login']);});
    }
  }

}
