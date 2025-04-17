import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order-details-modal',
  templateUrl: './order-details-modal.component.html',
  styleUrls: ['./order-details-modal.component.css']
})
export class OrderDetailsModalComponent implements OnInit {

  @Input() order!: any;
  @Output() dataEvent = new EventEmitter<any>();
  @Output() loadingStart = new EventEmitter<any>();
  @Output() loadingDone = new EventEmitter<any>();


  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
  }

  getTotalPrice() {
    let total = 0;

    for (const item of this.order.purchasedItems) {
      total += item.cartItem.quantity * item.cartItem.game.price.price;
    }

    return total.toFixed(2);
  }

  getTotalForItem(quantity: any, price: any) {
    return (price * quantity).toFixed(2);
  }

  ship() {
    this.loadingStart.emit()
    this.orderService.ship(this.order).subscribe({
      complete: () => this.dataEvent.emit()
    }
    );

  }

}
