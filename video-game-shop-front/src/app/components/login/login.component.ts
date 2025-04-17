import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onLoginSubmit() {
    if (this.loginForm.valid) {
      const emailValue = this.loginForm.get('email')?.value;
      const passwordValue = this.loginForm.get('password')?.value;
      const body = {
        email: emailValue,
        password: passwordValue,
      }
      this.authService.login(body).subscribe(data => {
        localStorage.setItem('jwt', data.accessToken);
        localStorage.setItem('role', data.role[0].name);
        console.log(localStorage.getItem('jwt'));
        if (data.role[0].name == "ROLE_USER") {
          this.router.navigate(['home']);;
        }
        if (data.role[0].name == "ROLE_ADMIN") {
          this.router.navigate(['profile/game-settings']);
        }
        if (data.role[0].name == "ROLE_MANAGER") {
          this.router.navigate(['profile/central-inventory']);
        }
        if (data.role[0].name == "ROLE_STAFF") {
          this.router.navigate(['profile/inventory-settings']);
        }

      });
    }
  }
}

