import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit {

  gameId?: number; // Store the game ID from the route parameter
  game: any; // Store the game details

  constructor(
    private route: ActivatedRoute,
    private featuredGamesService : FeaturedGamesService
  ) {}

  ngOnInit(): void {
    // Get the game ID from the route parameter
    const gameIdParam = this.route.snapshot.paramMap.get('id');
    if (gameIdParam !== null) {
      this.gameId = +gameIdParam;
      this.fetchGameDetails();
    }
  }

  fetchGameDetails(): void {
    this.featuredGamesService.getGameById(this.gameId).subscribe(
      data => {
        this.game = data;
      },
      error => {
        console.error('Error fetching game details:', error);
      }
    );
  }

}
