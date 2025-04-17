import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-manager',
  templateUrl: './add-manager.component.html',
  styleUrls: ['./add-manager.component.css']
})
export class AddManagerComponent implements OnInit {

  validateForm!: UntypedFormGroup;
  constructor(private fb: UntypedFormBuilder, private userService : UserService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      email: [null],
      password: [null],
    });
  }

  submitForm(): void {
      this.userService.registerManager(this.validateForm.value).subscribe();
  }

}
