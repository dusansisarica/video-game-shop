import { Component } from '@angular/core';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recommended-games',
  templateUrl: './recommended-games.component.html',
  styleUrls: ['./recommended-games.component.css']
})
export class RecommendedGamesComponent {

  recommendedGames: any[] = [];

  constructor(private gameService: FeaturedGamesService, private router: Router) {}

  ngOnInit(): void {
    this.loadRecommendedGames();
  }

  loadRecommendedGames(): void {
    const recentIds: number[] = JSON.parse(localStorage.getItem('recentGames') || '[]');
    this.gameService.getRecommendedGames(recentIds).subscribe(games => {
      this.recommendedGames = games;
    });
  }

  showDetails(gameId: number): void {
    this.router.navigate([`games/${gameId}`]);
  }

}
