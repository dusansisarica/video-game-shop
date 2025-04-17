import { Component, OnInit } from '@angular/core';
import { FormsModule, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms'; // Import FormsModule
import { FeaturedGamesService } from 'src/app/services/featured-games.service';
import { parse, addHours, addMinutes } from 'date-fns';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-add-game-modal',
  templateUrl: './add-game-modal.component.html',
  styleUrls: ['./add-game-modal.component.css']
})
export class AddGameModalComponent implements OnInit {

  constructor(private fb: UntypedFormBuilder, private videoGameService : FeaturedGamesService, private datePipe: DatePipe) { }
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
    this.validateForm = this.fb.group({
      title: [null, [Validators.required]],
      description: [null, [Validators.required]],
      price: [null, [Validators.required, Validators.min(0)]],
      release_date: [null, [Validators.required]],
      platforms: [null, [Validators.required]],
      genres: [null, [Validators.required]],
      image: [null, [Validators.required]],
    });

  }

  validateForm!: UntypedFormGroup;


  game: any = {
    title: '',
    description: '',
    price: 0,
    release_date: '',
    platforms: [], // Initialize with an empty array
    genres: [],    // Initialize with an empty array
    image: '',
  };

  submitForm(): void {
    if (this.validateForm.valid) {
      const dateValue = this.validateForm.get('release_date')!.value;
      // Now isoDate contains the ISO 8601 formatted date string
      const isoDate = this.datePipe.transform(dateValue, 'yyyy-MM-ddTHH:mm:ss.SSS');
      console.log(isoDate);
      console.log('Form submitted with values:', this.validateForm.value);

      this.validateForm.value.release_date = isoDate;
      // Perform the action to add a new game here
      this.videoGameService.addNewGame(this.validateForm.value).subscribe();
    }
  }


}
