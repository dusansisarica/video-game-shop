import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public featuredGames : any;
  constructor(private router: Router, private featuredGamesService: FeaturedGamesService) { }

  ngOnInit(): void {
    this.getAllGames();
  }

  private getAllGames(): void {
    this.featuredGamesService.getAllGames().subscribe(data => {
      this.featuredGames = data;
      console.log("A")

    }, error => {
      alert("Error");
    })
  }

}
