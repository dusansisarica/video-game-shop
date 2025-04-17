

import { Component, OnInit } from '@angular/core';
declare var L: any; // Deklaracija za Leaflet


@Component({
  selector: 'app-heatmap',
  templateUrl: './heatmap.component.html',
  styleUrls: ['./heatmap.component.css'],
})
export class HeatmapComponent implements OnInit {
  private map: any;
  private heatmapLayer: any;

  constructor() { }

  ngOnInit(): void {
    this.initMap(); // Inicijalizacija mape kada se komponenta učita
  }

  private initMap(): void {
    // Inicijalizacija mape sa centrom i zumiranjem
    this.map = L.map('map', {
      center: [44.8176, 20.4633], // Centar Srbije (Beograd)
      zoom: 7
    });

    // Dodavanje osnovnog sloja karte
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19
    }).addTo(this.map);

    // Dodavanje heatmap sloja
    this.heatmapLayer = L.heatLayer(this.getHeatmapData(), {
      radius: 25,
      blur: 15,
    }).addTo(this.map);
  }

  private getHeatmapData(): number[][] {
    // Vraća niz koordinata za heatmap
    return [
      [44.8176, 20.4633, 0.5], // Primer koordinata za Beograd
      [44.8212, 20.4572, 0.5], // Dodaj više tačaka
      [44.7833, 20.4719, 0.5],
      // Dodaj još podataka
    ];
  }

}
