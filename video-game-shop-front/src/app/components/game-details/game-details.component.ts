import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CartService } from 'src/app/services/cart.service';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';
import { ReviewService } from 'src/app/services/review.service';
import { ShopService } from 'src/app/services/shop.service';
import { WishListService } from 'src/app/services/wish-list.service';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit {

  gameId?: number; // Store the game ID from the route parameter
  game: any; // Store the game details
  gameReviews: any;
  isVisible: any;
  quantity: any;
  showReviews = false;
  showAvailableShops = false;
  shops!: any;

  constructor(
    private route: ActivatedRoute,
    private featuredGamesService: FeaturedGamesService,
    private reviewService: ReviewService,
    private datePipe: DatePipe,
    private cartService: CartService,
    private wishListService: WishListService,
    private message: NzMessageService,
    private shopService: ShopService
  ) { }

  ngOnInit(): void {
    const gameIdParam = this.route.snapshot.paramMap.get('id');
    if (gameIdParam !== null) {
      this.gameId = +gameIdParam;
      this.fetchGameDetails();
      this.getShopsForGame();
    }

  }

  calculateDate() {
    const normalDate = new Date(this.game.release_date[0], this.game.release_date[1] - 1, this.game.release_date[2]);
    return this.datePipe.transform(normalDate, 'MM/dd/yyyy');
  }

  getShopsForGame() {
    this.shopService.getShopsForGame(this.gameId).subscribe(data => {
      this.shops = data.shops;
    })
  }


  fetchGameDetails(): void {
    this.featuredGamesService.getGameById(this.gameId).subscribe(
      data => {
        this.game = data;
        console.log(this.game)
      },
      error => {
        console.error('Error fetching game details:', error);
      }
    );
    this.reviewService.getGameReviews(this.gameId).subscribe(data => {
      this.gameReviews = data
    })

  }

  buy() {
    this.isVisible = true
  }

  wishList() {
    this.wishListService.addToWishList(this.game.ID).subscribe(
    );
    this.message.create('success', `Uspešno dodato!`)
  }

  handleCancel() {
    this.isVisible = false
  }

  handleOk() {
    if (this.quantity != null && this.quantity != 0) {
      const body = {
        "game": {
          "id": this.game.ID
        },
        "quantity": this.quantity
      }
      this.cartService.addToCart(body).subscribe()
    }
    this.message.create('success', `Uspešno dodato!`)
    this.isVisible = false;
  }

  isUser() {
    return (localStorage.getItem("role") == "ROLE_USER");
  }

  isGuest() {
    return (localStorage.getItem("role") == null);
  }

  confirm() {

  }

  cancel() {

  }

  showReviewsFunc() {
    this.showReviews = !this.showReviews
    this.showAvailableShops = false
  }

  showAvailableShopsFunc() {
    this.showAvailableShops = !this.showAvailableShops
    this.showReviews = false

  }
}
