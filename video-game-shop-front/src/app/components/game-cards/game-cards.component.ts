import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WishListService } from 'src/app/services/wish-list.service';

@Component({
  selector: 'app-game-cards',
  templateUrl: './game-cards.component.html',
  styleUrls: ['./game-cards.component.css']
})
export class GameCardsComponent implements OnInit {

  constructor(private wishListService : WishListService, private router : Router) { }

  ngOnInit(): void {
  }

  @Input() games: any;

  buy(id : any) {

  }
  public addToWishList(id : any): void {
    this.wishListService.addToWishList(id).subscribe()
  }

  public gameDetails(id : any): void {
    this.router.navigate([`games/${id}`]);
  }

}
