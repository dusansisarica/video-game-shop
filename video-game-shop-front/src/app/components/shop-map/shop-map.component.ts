import { Component, Input, OnInit } from '@angular/core';
import * as L from 'leaflet';



@Component({
  selector: 'app-shop-map',
  templateUrl: './shop-map.component.html',
  styleUrls: ['./shop-map.component.css']
})
export class ShopMapComponent implements OnInit {

  @Input() shop: any;
  map: any;
  constructor() { }

  ngOnInit(): void {
    this.initializeMap();
  }

  initializeMap(): void {
    const mapElement = document.getElementById('map')!;
    const shopLocation: L.LatLngExpression = [this.shop.address.latitude, this.shop.address.longitude];

    this.map = L.map(mapElement).setView(shopLocation, 15);

    L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Tiles style by <a href="https://www.hotosm.org/" target="_blank">Humanitarian OpenStreetMap Team</a> hosted by <a href="https://openstreetmap.fr/" target="_blank">OpenStreetMap France</a>'
    }).addTo(this.map);

    const customIcon = L.icon({
      iconUrl: 'assets/marker-icon.png',
      iconSize: [40, 40],
      iconAnchor: [20, 40],
      popupAnchor: [0, -40]
    });

    L.marker(shopLocation, { icon: customIcon })
      .addTo(this.map)
      .bindPopup(`<b>${this.shop.name}</b><br>${this.shop.address.address}`)
      .openPopup();

    // Osigurajte da je prodavnica u centru nakon što se mapa u potpunosti učita
    this.map.whenReady(() => {
      this.map.setView(shopLocation, 15);
    });
    // const mapElement = document.getElementById('map')!;
    // this.map = L.map(mapElement).setView([this.shop.address.longitude, this.shop.address.latitude], 13
    // );
    // L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
    //   attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Tiles style by <a href="https://www.hotosm.org/" target="_blank">Humanitarian OpenStreetMap Team</a> hosted by <a href="https://openstreetmap.fr/" target="_blank">OpenStreetMap France</a>'
    // }).addTo(this.map);

    // const customIcon = L.icon({
    //   iconUrl: 'assets/marker-icon.png',
    //   iconSize: [40, 40],
    //   iconAnchor: [20, 40],
    //   popupAnchor: [0, -40]
    // });

    // L.marker([this.shop.address.latitude, this.shop.address.longitude], { icon: customIcon })
    //   .addTo(this.map)
    //   .bindPopup(`<b>${this.shop.name}</b><br>${this.shop.address.street}`)
    //   .openPopup();

    // this.map.setZoom(14);

    // L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    //   attribution: '&copy; OpenStreetMap contributors'
    // }).addTo(this.map);
    // const customIcon = L.icon({
    //   iconUrl: 'assets/marker-icon.png',
    //   iconSize: [32,32]
    // })


    // L.marker([this.shop.address.latitude, this.shop.address.longitude],{icon:customIcon}).addTo(this.map)
    //   .bindPopup(this.shop.name)
    //   .openPopup();
  }


}
