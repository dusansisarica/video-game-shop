import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';
import Swiper from 'swiper';

@Component({
  selector: 'app-featured-games',
  templateUrl: './featured-games.component.html',
  styleUrls: ['./featured-games.component.css']
})
export class FeaturedGamesComponent implements OnInit {

  public featuredGames: any;
  activeIndex = 0;
  currentImage = '';
  private mySwiper!: Swiper;
  constructor(private router: Router, private featuredGamesService: FeaturedGamesService) { }

  ngOnInit(): void {
    this.getAllGames();
    this.startImageCarousel();
  }

  ngAfterViewInit(): void {
    this.mySwiper = new Swiper('.swiper-container', {
      slidesPerView: 1,
      centeredSlides: false,
      spaceBetween: 25,
      keyboard: true,
      freeMode: true,

    });
  }

  private getAllGames(): void {
    this.featuredGamesService.getAllGames(1).subscribe(data => {
      this.featuredGames = data;
      console.log("A")

    }, error => {
      alert("Error");
    })
  }


  startImageCarousel(): void {
    setInterval(() => {
      this.activeIndex = (this.activeIndex + 1) % this.featuredGames.length;
      this.currentImage = this.featuredGames[this.activeIndex].image;
    }, 3000);
  }



}
