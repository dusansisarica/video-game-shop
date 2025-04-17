import { Component, OnInit } from '@angular/core';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';

@Component({
  selector: 'app-game-settings',
  templateUrl: './game-settings.component.html',
  styleUrls: ['./game-settings.component.css']
})
export class GameSettingsComponent implements OnInit {
  games?: any;
  isVisible = false;
  isVisibleUpdate = false;
  isVisiblePrice = false;
  gameId?: any;
  totalNumber: any;
  inputValue: string = "";

  constructor(private videoGameService: FeaturedGamesService) { }

  ngOnInit(): void {
    this.getAllGames();
  }

  getAllGames() {
    this.videoGameService.getAllGamesGenres(1, [], []).subscribe(data => {
      this.games = data.games;
      this.totalNumber = data.totalSize;
    })
  }

  getSearch() {
    this.getData(1);
  }

  getData(page: any) {
    this.videoGameService.searchGames(page, this.inputValue).subscribe(data => {
      this.games = data.games;
      this.totalNumber = data.totalSize;
    })
  }

  getGameId(event: { id: any, action: string }) {
    this.gameId = event.id;
    console.log(this.gameId)
    if (event.action === 'changePrice') {
      this.isVisiblePrice = true;
    } else if (event.action === 'updateGame') {
      this.isVisibleUpdate = true;
    }
    //this.isVisibleUpdate = true;
  }

  handleCancel() {
    this.isVisible = false
  }

  handleOk() {

  }

  handleCancelUpdate() {
    this.isVisibleUpdate = false
  }

  handleCancelPrice() {
    this.isVisiblePrice = false
  }

  handleOkUpdate() {

  }

  showModal() {
    this.isVisible = true;
  }

  showModalUpdate(id: any) {
    this.isVisibleUpdate = true;
  }

  showModalPrice(id: any) {
    this.isVisiblePrice = true;
  }


}
