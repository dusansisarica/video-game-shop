import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';
import { WishListService } from 'src/app/services/wish-list.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public featuredGames : any;
  length! : any;
  constructor(private router: Router, private featuredGamesService: FeaturedGamesService,
    private wishListService : WishListService) { }

  ngOnInit(): void {
    this.getAllGames();
  }

  private getAllGames(): void {
    this.featuredGamesService.getFeaturedGames().subscribe(data => {
      this.featuredGames = data;
      console.log(data)
      console.log("A")

    }, error => {
      alert("Error");
    })
  }

  public addToWishList(id : any): void {
    this.wishListService.addToWishList(id).subscribe()
  }

  public gameDetails(id : any): void {
    this.router.navigate([`games/${id}`]);
  }

  

}
