import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { MapboxService } from 'src/app/services/mapbox.service'; // Importuj MapboxService


@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  public user: any;
  public name = '';
  profileForm!: FormGroup;

  cities: any[] = [];
  streets: string[] = [];
  cityQuery: string = '';
  streetQuery: string = '';
  selectedCity: string = '';
  selectedStreet: string = '';
  lat: string = '';
  lon: string = '';


  constructor(private userService: UserService, private fb: FormBuilder, private mapboxService: MapboxService) {
    this.getLoggedInUser();
  }

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      name: [''],
      surname: [''],
      address: [''],
      city: [''],
      country: ['']
    });

    this.getLoggedInUser();
    console.log(this.user);

    // if (this.user) {
    //   this.profileForm = this.fb.group({
    //     name: [''],      // Can be empty
    //     surname: [''],   // Can be empty
    //     address: [''],   // Can be empty
    //     city: [''],      // Can be empty
    //     country: ['']    // Can be empty
    //   });

    //   this.profileForm.patchValue({
    //     name: this.user.name,
    //     surname: this.user.surname,
    //     address: this.user.address.address,
    //     city: this.user.address.city,
    //     country: this.user.address.country
    //   });
    // }

  }

  getLoggedInUser() {
    this.userService.getLoggedInUser().subscribe(data => {
      this.user = data;
      console.log(this.user.name);
      // this.profileForm = this.fb.group({
      //   name: [''],
      //   surname: [''],
      //   address: [''],
      //   city: [''],
      //   country: ['']
      // });

      this.profileForm.patchValue({
        name: this.user.name,
        surname: this.user.surname,
        address: this.user.address.address,
        city: this.user.address.city.name,
        country: this.user.address.city.countryDto.name
      });
    });

  }

  onSubmit() {
    console.log(this.user);

    const body = {
      email: this.user.email,
      name: this.profileForm.get('name')?.value,
      surname: this.profileForm.get('surname')?.value,
      address: {
        address: this.profileForm.get('address')?.value,
        city: {
          countryDto: {
            name: this.profileForm.get('country')?.value,
          },
          name: this.profileForm.get('city')?.value,
          longitude: this.lon,
          latitude: this.lat
        }
      },
      jwt: ""
    }
    this.userService.changeUserDetails(body).subscribe();
    //  this.getLoggedInUser();
  }
  onCityInput(event: any) {
    const query = event.target.value;
    if (query.length > 2) {
      this.mapboxService.searchCity(query).subscribe((response) => {
        // Filtriranje rezultata da bi se dobili samo gradovi
        this.cities = response
          .map((item: any) => ({
            name: item.address.city,
            lat: item.lat,
            lon: item.lon
          })) // Ekstraktuj samo gradove
          .filter((city: any) => city.name !== undefined) // Ukloni undefined vrednosti
          .filter((value: string, index: number, self: string[]) => self.indexOf(value) === index); // Ukloni duplikate
      });
    } else {
      this.cities = [];
    }

  }

  // onStreetInput(event: any) {
  //   const city = this.profileForm.get('city')?.value;
  //   const streetQuery = event.target.value;
  //   if (city && streetQuery.length > 2) {
  //     this.mapboxService.searchStreet(city, streetQuery).subscribe((response) => {
  //       this.streets = response.map((item: any) => item.display_name);
  //     });
  //   } else {
  //     this.streets = [];
  //   }
  // }

  onStreetInput(event: any) {
    const streetQuery = event.target.value;
    const city = this.profileForm.get('city')?.value;
    if (streetQuery.length > 2) {
      this.mapboxService.searchStreet(city, streetQuery).subscribe((response) => {
        // Filtriranje rezultata da bi se dobili samo nazivi ulica
        this.streets = response
          .map((item: any) => item.address.road) // Ekstraktuj samo nazive ulica
          .filter((road: string) => road !== undefined) // Ukloni undefined vrednosti
          .filter((value: string, index: number, self: string[]) => self.indexOf(value) === index); // Ukloni duplikate
      });
    } else {
      this.streets = [];
    }
  }




  // Pretraga gradova
  // onCityInput(event: any) {
  //   this.cityQuery = event.target.value;
  //   if (this.cityQuery.length > 2) {
  //     this.mapboxService.searchCity(this.cityQuery).then((response: any) => {
  //       this.cities = response.body.features.map((feature: any) => feature.place_name);
  //     });
  //   } else {
  //     this.cities = []; // Očisti predloge ako je unos manji od 3 karaktera
  //   }
  // }

  // Odabir grada
  onSelectCity(city: any) {
    this.profileForm.get('city')?.setValue(city.name); // Postavi ime grada
    this.lat = city.lat; // Sačuvaj latitude
    this.lon = city.lon; // Sačuvaj longitude
    this.cities = []; // Očisti predloge
    console.log({ city })
  }


  // Pretraga ulica
  // onStreetInput(event: any) {
  //   this.streetQuery = event.target.value;
  //   if (this.selectedCity && this.streetQuery.length > 2) {
  //     this.mapboxService.searchStreet(this.selectedCity, this.streetQuery).then((response: any) => {
  //       this.streets = response.body.features.map((feature: any) => feature.place_name);
  //     });
  //   } else {
  //     this.streets = []; // Očisti predloge
  //   }
  // }

  // Odabir ulice
  onSelectStreet(street: string) {
    this.selectedStreet = street;
    console.log({ street })
    this.streets = []; // Očisti predloge
    this.profileForm.patchValue({ address: street }); // Postavi unos na izabranu ulicu
  }

}
