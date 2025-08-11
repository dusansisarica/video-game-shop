import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { NzMessageService } from 'ng-zorro-antd/message';
import { OrderService } from 'src/app/services/order.service';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-previous-orders',
  templateUrl: './previous-orders.component.html',
  styleUrls: ['./previous-orders.component.css']
})
export class PreviousOrdersComponent implements OnInit {

  orders: any[] = []; 
  review?: string;
  isVisible = false;
  isOkLoading = false;
  rate?: any;
  gameId : any;
  gameName : any;

  showModal(id : any): void {
    this.isVisible = true;
    this.gameId = id;
  }

  handleOk(): void {
    console.log(this.gameId)
    const review = {
      "game" : {
        "ID" : this.gameId
      },
      "comment" : this.review,
      "rating" : this.rate
    }
    this.reviewService.writeReview(review).subscribe(
    );
    this.isVisible = false;
    this.message.create('success', `Uspešno dodato!`)
  }

  getGameName() {
    for (const order of this.orders) {
      // Iterate through the purchased items of each order
      for (const purchasedItem of order.purchasedItems) {
        // Check if the purchased item's game ID matches the ID you're looking for
        if (purchasedItem.cartItem.game.id === this.gameId) {
          // If a match is found, store the game name and break out of the loop
          this.gameName = purchasedItem.cartItem.game.title;
          break;
        }
      }
    }
    return this.gameName;
  }

  handleCancel(): void {
    this.isVisible = false;
  }


  constructor(private orderService: OrderService, 
    private reviewService : ReviewService,
    private message: NzMessageService) {
  } 

  ngAfterViewInit() {
    this.orderService.getUsersOrders().subscribe((data) => {
      this.orders = data;
    });

  }

  ngOnInit(): void {
    // this.orderService.getUsersOrders().subscribe((data) => {
    //   this.orders = data;
    // });
  }

  
  

  displayedColumns: string[] = ['date', 'gameInfo', 'quantity', 'price', 'status'];

  showReviewButton(order : any) {
    if (order.orderStatus == "DELIVERED") {
      return true;
    }
    return false;
  }

  getOrderPrice(item: any): number {
    return item.cartItem.quantity * item.cartItem.game.price.price;
  }

}
