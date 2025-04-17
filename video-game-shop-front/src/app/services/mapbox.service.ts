import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MapboxService {
  private apiUrl = 'https://nominatim.openstreetmap.org/search';

  constructor(private http: HttpClient) { }

  searchCity(query: string): Observable<any> {
    return this.http.get<any>(this.apiUrl, {
      params: {
        q: `${query}, Serbia`, // Pretražujemo gradove u Srbiji
        format: 'json',
        addressdetails: '1',
        limit: '5'
      }
    });
  }


  // Pretraga ulica
  searchStreet(city: string, street: string): Observable<any> {
    return this.http.get<any>(this.apiUrl, {
      params: {
        city: city,
        street: street,
        format: 'json',
        addressdetails: '1', // Da dobijemo detalje o adresi
        limit: '5', // Ograničavamo rezultate na 5
      }
    });
  }

}


// import { HttpClient } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// // @ts-ignore
// import * as mapboxgl from '@mapbox/mapbox-sdk/services/geocoding';
// import { environment } from 'src/environments/environment';
// @Injectable({
//   providedIn: 'root'
// })
// export class MapboxService {

//   private geocodingClient;
//   private apiUrl = 'https://api.mapbox.com/geocoding/v5/mapbox.places';


//   constructor(private http: HttpClient) {
//     // Tvoj Access Token
//     this.geocodingClient = mapboxgl({
//       accessToken: 'pk.eyJ1IjoiZHVzYW5zaXNhcmljYSIsImEiOiJjbTFzOGFuYWowNDk0MmlyM3hwb3FzdzJnIn0.aXk42fAnewU0SwPFFoWShQ'
//     });
//   }

//   searchCity(query: string) {
//     return this.http.get(`${this.apiUrl}/${encodeURIComponent(query)}.json`, {
//       params: {
//         access_token: environment.accessToken,
//         countries: 'RS',
//         types: 'locality', // Samo lokaliteti (gradovi)
//         limit: '5'
//       }
//     }).toPromise();
//   }

//   searchStreet(city: string, query: string) {
//     return this.http.get(`${this.apiUrl}/${encodeURIComponent(query)}.json`, {
//       params: {
//         access_token: environment.accessToken,
//         countries: 'RS',
//         proximity: `ip`, // Proximity za tačniji rezultat
//         types: 'address', // Samo adrese (ulice)
//         limit: '5'
//       }
//     }).toPromise();
//   }


//   // Ova funkcija dobija gradove
//   // searchCity(query: string) {
//   //   return this.geocodingClient.forwardGeocode({
//   //     query: query,
//   //     countries: ['RS'], // Pretraga samo u Srbiji
//   //     autocomplete: true,
//   //     limit: 5 // Vraća samo 5 predloga
//   //   }).send();
//   // }

//   // // Ova funkcija dobija ulice za dati grad
//   // searchStreet(city: string, query: string) {
//   //   return this.geocodingClient.forwardGeocode({
//   //     query: `${query}, ${city}`, // Pretraga ulica u odabranom gradu
//   //     countries: ['RS'], // Samo za Srbiju
//   //     autocomplete: true,
//   //     limit: 5
//   //   }).send();
//   // }

// }
