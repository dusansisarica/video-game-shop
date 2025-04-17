import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart: any;
  map = new Map();

  constructor(private cartService: CartService, private orderService: OrderService, private videoGameService: FeaturedGamesService) { }

  ngOnInit(): void {
    this.getCart();

  }

  getCart(): void {
    // Call your cart service to get the user's cart
    this.cartService.getCart().subscribe(cart => {
      this.cart = cart;

    });
  }

  removeItem(id : any) : void {
    console.log(id)
    this.cartService.removeItem(id).subscribe(cart => {
      this.cart = cart;
    });
  }

  getTotalPrice() {
    let total = 0;
    for (const cartItem of this.cart.cartItems) {
      total += cartItem.game.price.price * cartItem.quantity;
      console.log(cartItem)
    }
    return total.toFixed(2);
  }

  hasItems() {
    return this.cart.cartItems.length !== 0
  }

  buy() {
    this.orderService.makeOrder().subscribe( data =>
      {
        this.getCart();
      }
    );
  }

  getTotalQuantity() {
    this.cart.cartItems.forEach((cartItem: { game: { ID: any; }; }) => {
      let quantity;
      this.videoGameService.getTotalQuantity(cartItem.game.ID).subscribe(data => {
        quantity = data;
      })
      this.map.set(cartItem.game.ID, quantity)
    });
    console.log(this.map);

  }

}
