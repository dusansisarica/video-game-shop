import { Component, Input, OnInit } from '@angular/core';
import { ShopService } from 'src/app/services/shop.service';

@Component({
  selector: 'app-available-shops',
  templateUrl: './available-shops.component.html',
  styleUrls: ['./available-shops.component.css']
})
export class AvailableShopsComponent implements OnInit {

  @Input() shops : any;
  isVisible = false;
  shop : any
  constructor(private shopService : ShopService) { }

  ngOnInit(): void {
  }

  showOnMap(shop : any) {
    this.isVisible = true
    this.shop = shop;
  }

  handleCancel() {
    this.isVisible = false
  }

}
