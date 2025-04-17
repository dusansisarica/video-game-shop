import { Component, OnInit } from '@angular/core';
import { ShopService } from 'src/app/services/shop.service';

@Component({
  selector: 'app-order-cards',
  templateUrl: './order-cards.component.html',
  styleUrls: ['./order-cards.component.css']
})
export class OrderCardsComponent implements OnInit {

  items! : any[];

  constructor(private shopService : ShopService) { }

  ngOnInit(): void {
    this.getOrders();
  }

  getOrders() {
    this.shopService.getOrdersShop().subscribe( data => {
      this.items = data;
    })
  }

  accept(id : any) {
    this.shopService.accept(id).subscribe( data => {
      this.items = data;
    })
  }

  decline(id : any) {
    this.shopService.decline(id).subscribe( data => {
      this.items = data;
    })
  }

  ship(id : any) {
    this.shopService.ship(id).subscribe( data => {
      this.items = data;
    })
  }
}
