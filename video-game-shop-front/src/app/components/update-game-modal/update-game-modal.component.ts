import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';

@Component({
  selector: 'app-update-game-modal',
  templateUrl: './update-game-modal.component.html',
  styleUrls: ['./update-game-modal.component.css']
})
export class UpdateGameModalComponent implements OnInit {

  @Input() gameId: any;

  constructor(private fb: UntypedFormBuilder, private videoGameService: FeaturedGamesService, private datePipe: DatePipe) {
    this.validateForm = this.fb.group({
      title: [null, [Validators.required]],
      description: [null, [Validators.required]],
      price: [null, [Validators.required, Validators.min(0)]],
      release_date: [null, [Validators.required]],
      priceRange: [null, null],
      platforms: [null, [Validators.required]],
      genres: [null, [Validators.required]],
      image: [null, [Validators.required]],
    });
  }

  platformOptions: string[] = [
    "PC",
    "PS5",
    "PS4",
    "XboxSeriesX",
    "XboxSeriesS",
    "XboxOne",
    "NintendoSwitch",
    "Nintendo3DS",
    "PSP",
    "VR"];
  genreOptions: string[] = [
    "ACTION",
    "ADVENTURE",
    "RPG",
    "FPS",
    "TPS",
    "SIMULATION",
    "STRATEGY",
    "SPORT",
    "HORROR",
    "PLATFORMER",
    "PUZZLE",
    "FIGHTING",
    "RACING",
    "MMO"
  ];


  ngOnInit(): void {
    // this.validateForm = this.fb.group({
    //   title: [null, [Validators.required]],
    //   description: [null, [Validators.required]],
    //   price: [null, [Validators.required, Validators.min(0)]],
    //   release_date: [null, [Validators.required]],
    //   platforms: [null, [Validators.required]],
    //   genres: [null, [Validators.required]],
    //   image: [null, [Validators.required]],
    // });
    this.getGameDetails();
  }

  getGameDetails() {
    this.videoGameService.getGameById(this.gameId).subscribe(data => {
      console.log(data)
      this.validateForm = this.fb.group({
        title: [data.title, [Validators.required]],
        description: [data.description, [Validators.required]],
        price: [data.price.price, [Validators.required, Validators.min(0)]],
        release_date: [new Date(data.release_date[0], data.release_date[1] - 1, data.release_date[2], data.release_date[3], data.release_date[4]), [Validators.required]],
        priceRange: [[new Date(data.price.startDate[0], data.price.startDate[1] - 1, data.price.startDate[2]), new Date(data.price.endDate[0], data.price.endDate[1] - 1, data.price.endDate[2])]],
        platforms: [data.platforms, [Validators.required]],
        genres: [data.genres, [Validators.required]],
        image: [data.image, [Validators.required]],
      });
    })
  }

  validateForm!: UntypedFormGroup;


  submitForm(): void {
    if (this.validateForm.valid) {
      const dateValue = this.validateForm.get('release_date')!.value;
      // Now isoDate contains the ISO 8601 formatted date string
      const isoDate = this.datePipe.transform(dateValue, 'yyyy-MM-ddTHH:mm:ss.SSS');
      console.log(isoDate);
      console.log('Form submitted with values:', this.validateForm.value);
      console.log(this.gameId)
      this.validateForm.value.release_date = isoDate;
      const game = {
        ID: this.gameId,
        title: this.validateForm.get('title')!.value,
        description: this.validateForm.get('description')!.value,
        price: this.validateForm.get('price')!.value,
        release_date: this.validateForm.get('release_date')!.value,
        platforms: this.validateForm.get('platforms')!.value, // Initialize with an empty array
        genres: this.validateForm.get('genres')!.value,    // Initialize with an empty array
        image: this.validateForm.get('image')!.value,
      };
      console.log(game)
      // Perform the action to add a new game here
      this.videoGameService.updateGame(game).subscribe();
    }
  }

}
