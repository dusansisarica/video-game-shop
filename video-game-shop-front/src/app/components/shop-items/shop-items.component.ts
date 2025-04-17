import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CartService } from 'src/app/services/cart.service';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';
import { WishListService } from 'src/app/services/wish-list.service';

@Component({
  selector: 'app-shop-items',
  templateUrl: './shop-items.component.html',
  styleUrls: ['./shop-items.component.css']
})
export class ShopItemsComponent implements OnInit {
  games!: any[]
  totalNumber: any;
  genres!: String[];
  platforms!: String[];
  genresSelected: String[] = [];
  platformsSelected: String[] = [];
  gameId!: any;
  inputValue: string = "";
  totalQuantity!: any;
  platformMenuOpen = false;  // Koristi se za otvaranje/zatvaranje platform menija
  genreMenuOpen = false;     // Koristi se za otvaranje/zatvaranje žanrova
  sortMenuOpen = false;
  sortBy = 'title';
  sortDirection = 'asc';



  constructor(private videoGameService: FeaturedGamesService,
    private wishListService: WishListService,
    private router: Router,
    private cartService: CartService,
    private message: NzMessageService) { }

  ngOnInit(): void {
    // if (!window.location.hash) {
    //   window.location.href = window.location.href + '#loaded';
    //   window.location.reload();
    // }
    this.getGames([], []);
    this.getGenres();
    this.getPlatforms();

  }

  getGenres() {
    this.videoGameService.getGenres().subscribe(data => {
      this.genres = data;
      console.log("GENRES: " + this.genres)
    })
  }

  getPlatforms() {
    this.videoGameService.getPlatforms().subscribe(data => {
      this.platforms = data;
    })
  }


  toggleSortMenu() {
    this.sortMenuOpen = !this.sortMenuOpen;
  }


  getGames(genres: String[], platforms: String[]) {
    this.videoGameService.getAllWithParams(1, genres, platforms, this.inputValue, this.sortBy, this.sortDirection).subscribe(data => {
      this.games = data.games;
      this.totalNumber = data.totalSize;
    })
  }

  sortGames() {
    this.getGames(this.genresSelected, this.platformsSelected);
  }

  getSearch() {
    this.getData(1);
  }

  addGenre(genre: any) {
    const index = this.genresSelected.indexOf(genre);       // Find the index of the target item
    if (index !== -1) {
      this.genresSelected.splice(index, 1); // Remove one item at the found index
    }
    else {
      this.genresSelected.push(genre);
    }
    console.log(this.genresSelected);
    this.getGames(this.genresSelected, this.platformsSelected);
  }

  addPlatform(platform: any) {
    const index = this.platformsSelected.indexOf(platform);       // Find the index of the target item
    if (index !== -1) {
      this.platformsSelected.splice(index, 1); // Remove one item at the found index
    }
    else {
      this.platformsSelected.push(platform);
    }
    //console.log(this.genresSelected);
    this.getGames(this.genresSelected, this.platformsSelected)
  }

  getData(page: any) {
    console.log(page);
    this.videoGameService.getAllWithParams(page, this.genresSelected, this.platformsSelected, this.inputValue, this.sortBy, this.sortDirection).subscribe(data => {
      this.games = data.games;
      this.totalNumber = data.totalSize;
    })
  }

  togglePlatformMenu(): void {
    this.platformMenuOpen = !this.platformMenuOpen;  // Menja stanje menija za platforme
  }

  toggleGenreMenu(): void {
    this.genreMenuOpen = !this.genreMenuOpen;  // Menja stanje menija za žanrove
  }


  wishList(id: any) {
    this.wishListService.addToWishList(id).subscribe();
    this.message.create('success', `Uspešno dodato!`)

  }

  showDetails(id: any) {
    this.router.navigate([`games/${id}`])
  }

  isVisible = false;
  quantity!: any;


  buy(id: any) {
    this.gameId = id;
    this.isVisible = true
    this.getTotalQuantity();
  }

  handleCancel() {
    this.isVisible = false;
    this.quantity = ""
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
    this.message.create('success', `Uspešno dodato!`)
    this.quantity = ""
  }

  isUser() {
    return (localStorage.getItem("role") == "ROLE_USER")
  }

  getTotalQuantity() {
    return this.videoGameService.getTotalQuantity(this.gameId).subscribe(data => {
      console.log(data);
      this.totalQuantity = data;
    });
  }

  isSelected(platform: String): boolean {
    console.log("Selektovano je " + this.platformsSelected.includes(platform))
    return this.platformsSelected.includes(platform);
  }

  isSelectedGenre(genre: String): boolean {
    console.log("Selektovano je " + this.genresSelected.includes(genre))
    return this.genresSelected.includes(genre);
  }

}
