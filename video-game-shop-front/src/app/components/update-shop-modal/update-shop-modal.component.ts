import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { ShopService } from 'src/app/services/shop.service';

@Component({
  selector: 'app-update-shop-modal',
  templateUrl: './update-shop-modal.component.html',
  styleUrls: ['./update-shop-modal.component.css']
})
export class UpdateShopModalComponent implements OnInit {

  @Input() shopId : any;
  validateForm!: UntypedFormGroup;
  shop : any;
  @Output() dataEvent = new EventEmitter<any>();


  constructor(private shopService : ShopService, private fb: UntypedFormBuilder) {
    this.validateForm = this.fb.group({
      name: [null],
      address: [null, [Validators.required]],
      city: [null, [Validators.required]],
      country: [null, [Validators.required]],
      longitude: [null, [Validators.required]],
      latitude: [null, [Validators.required]],
    });
   }

  ngOnInit(): void {
    this.getShop();
  }

  submitForm() {
    const body = {
      "id" : this.shopId,
      "name" : this.validateForm.get('name')!.value,
      "address" : {
        "address" : this.validateForm.get('address')!.value,
        "city" : {
          name : this.validateForm.get('city')!.value,
          countryDto : {
            name : this.validateForm.get('country')!.value,
          }
        },
        "longitude" : this.validateForm.get('longitude')!.value,
        "latitude" : this.validateForm.get('latitude')!.value
      },
      "items" : []
    }
    this.shopService.updateShop(body).subscribe();
    this.dataEvent.emit();
  }

  getShop() {
    this.shopService.getShop(this.shopId).subscribe( data => {
      this.shop = data;
      this.validateForm = this.fb.group({
        name: [data.name],
        address: [data.address.address, [Validators.required]],
        city: [data.address.city.name, [Validators.required]],
        country: [data.address.city.countryDto.name, [Validators.required]],
        longitude: [data.address.longitude, [Validators.required]],
        latitude: [data.address.latitude, [Validators.required]],
      });
    })
  }

  deleteShop(id : any) {
    this.shopService.deleteShop(id).subscribe();
    this.dataEvent.emit();
  }


}
