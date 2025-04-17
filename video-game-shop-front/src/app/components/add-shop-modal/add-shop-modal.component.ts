import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { ShopService } from 'src/app/services/shop.service';

@Component({
  selector: 'app-add-shop-modal',
  templateUrl: './add-shop-modal.component.html',
  styleUrls: ['./add-shop-modal.component.css']
})
export class AddShopModalComponent implements OnInit {

  validateForm!: UntypedFormGroup;
  @Output() dataEvent = new EventEmitter<any>();


  constructor(private fb: UntypedFormBuilder, private shopService : ShopService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      address: [null, [Validators.required]],
      city: [null, [Validators.required]],
      country: [null, [Validators.required]],
      longitude: [null, [Validators.required]],
      latitude: [null, [Validators.required]],
    });

  }

  submitForm() {
    const body = {
      "name" : this.validateForm.get('name')!.value,
      "address" : {
        "address" : this.validateForm.get('address')!.value,
        "city": {
          "name" : this.validateForm.get('city')!.value,
          "countryDto" : {
            "name" :this.validateForm.get('country')!.value,
          }
        },
        "longitude" : this.validateForm.get('longitude')!.value,
        "latitude" : this.validateForm.get('latitude')!.value
      },
      "items" : [],
      "deleted" : false
    }
    this.shopService.addShop(body).subscribe();
    this.dataEvent.emit();
  }

}
