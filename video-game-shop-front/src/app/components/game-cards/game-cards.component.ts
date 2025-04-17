import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';
import { WishListService } from 'src/app/services/wish-list.service';

@Component({
  selector: 'app-game-cards',
  templateUrl: './game-cards.component.html',
  styleUrls: ['./game-cards.component.css']
})
export class GameCardsComponent implements OnInit {

  constructor(private wishListService: WishListService, private router: Router, private cartService: CartService,
    private videoGameService: FeaturedGamesService) { }

  ngOnInit(): void {
  }

  @Input() games: any;
  @Input() admin = false;
  @Output() dataEvent = new EventEmitter<any>();
  gameId!: any;
  isVisible = false;
  quantity!: any;


  buy(id: any) {
    this.gameId = id;
    this.isVisible = true

  }

  handleCancel() {
    this.isVisible = false;
  }

  handleOk() {
    if (this.quantity != null && this.quantity != 0) {
      const body = {
        "game": {
          "id": this.gameId
        },
        "quantity": this.quantity
      }
      this.cartService.addToCart(body).subscribe()
    }
    this.isVisible = false;
  }

  public addToWishList(id: any): void {
    this.wishListService.addToWishList(id).subscribe()
  }

  public gameDetails(id: any): void {
    this.router.navigate([`games/${id}`]);
  }

  isUser() {
    return (localStorage.getItem("role") == "ROLE_USER")
  }

  isAdmin() {
    return (localStorage.getItem("role") == "ROLE_ADMIN")
  }

  updateGame(id: any) {
    this.dataEvent.emit({ id: id, action: 'updateGame' });
  }

  changePrice(id: any) {
    this.dataEvent.emit({ id: id, action: 'changePrice' });
  }


  deleteGame(id: any) {
    this.videoGameService.deleteGame(id).subscribe(data => {
      this.games = data;
    })
  }

  show() {
    return this.admin;
  }

}
