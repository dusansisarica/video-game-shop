import { Component, OnInit } from '@angular/core';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-recent-games',
  templateUrl: './recent-games.component.html',
  styleUrls: ['./recent-games.component.css']
})
export class RecentGamesComponent implements OnInit {

  recentGames: any[] = [];

  constructor(private gameService: FeaturedGamesService, private router: Router) {}

  ngOnInit(): void {
    this.loadRecentGames();
  }

  loadRecentGames(): void {
  const recentIds: number[] = JSON.parse(localStorage.getItem('recentGames') || '[]');
  this.recentGames = [];

  recentIds.forEach(id => {
    this.gameService.getGameById(id).subscribe(game => {
      this.recentGames.push(game);
    });
  });
  }

  showDetails(gameId: number): void {
    this.router.navigate([`games/${gameId}`]);
  }
}
