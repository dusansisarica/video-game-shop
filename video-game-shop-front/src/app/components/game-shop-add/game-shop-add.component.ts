import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';
import { ShopService } from 'src/app/services/shop.service';

@Component({
  selector: 'app-game-shop-add',
  templateUrl: './game-shop-add.component.html',
  styleUrls: ['./game-shop-add.component.css']
})
export class GameShopAddComponent implements OnInit {

  validateForm!: UntypedFormGroup;
  games!: any[];
  selectedGame: any;
  selectedGameID: any;

  @Input() shopId: any;


  constructor(private fb: UntypedFormBuilder, private videoGameService: FeaturedGamesService, private shopService: ShopService) {
    this.validateForm = this.fb.group({
      game: [null],
      quantity: [null, [Validators.required]],
    });

  }

  ngOnInit(): void {
    this.getGames();
  }

  getGames() {
    this.videoGameService.getAllWithParams(1, [], [], "", "title", "asc").subscribe(data => {
      this.games = data.games;
      console.log(this.games)
      this.validateForm = this.fb.group({
        game: [null],
        quantity: [null, [Validators.required]],
      });

    })
  }

  submitForm() {
    if (this.validateForm.valid && this.validateForm.get('game')!.value != null) {

      const body = {
        "gameId": this.validateForm.get('game')!.value,
        "quantity": this.validateForm.get('quantity')!.value,
        "shopId": this.shopId,
        "game": {
          "ID": this.validateForm.get('game')!.value
        },
        "reserved": 0
      }
      this.shopService.addGame(body).subscribe();
    }
  }

  updateSelectedGame() {
    this.selectedGameID = this.selectedGame.ID; // Assuming the ID property exists in your game object.
  }

  onGameSelectionChange(selectedGame: any) {
    console.log(selectedGame);
    // Now selectedGame should contain the selected game object.
  }


}
