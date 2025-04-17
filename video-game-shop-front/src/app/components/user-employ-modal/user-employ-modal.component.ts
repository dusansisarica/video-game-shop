import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { ShopService } from 'src/app/services/shop.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-employ-modal',
  templateUrl: './user-employ-modal.component.html',
  styleUrls: ['./user-employ-modal.component.css']
})
export class UserEmployModalComponent implements OnInit {

  @Input() user : any;
  shops! : any[];
  validateForm!: UntypedFormGroup;
  @Output() dataEvent = new EventEmitter<any>();


  constructor(private shopService : ShopService,private fb: UntypedFormBuilder, private userService : UserService) {
    this.validateForm = this.fb.group({
      name: [null],
      shops: [null],
    });
   }


  ngOnInit(): void {
    this.getDetails()
  }

  getDetails() {
    this.shopService.getAll(1).subscribe(data => {
      this.shops = data.shops
      this.validateForm = this.fb.group({
        name: [this.user.name, [Validators.required]],
        shops: [null],
      });
    })
  }

  getShopName(shop : any) {
    return `${shop.name}, ${shop.address.address}, ${shop.address.city}`;
  }
  
  submitForm() {
    const body = {
      "userId" : this.user.ID,
      "shopId" : this.validateForm.get("shops")!.value
    }
    console.log(body)
    this.userService.employUser(body).subscribe();
    this.dataEvent.emit();
  }

  getUserName() {
    return `${this.user.name} ${this.user.surname}`;
  }


}
