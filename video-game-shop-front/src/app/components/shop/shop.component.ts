import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ShopService } from 'src/app/services/shop.service';
import * as L from 'leaflet';
import { WishListService } from 'src/app/services/wish-list.service';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  shopId?: number;
  shop: any;
  map: any; // Leaflet map instance
  gameItems: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private shopService: ShopService,
    private wishListService: WishListService
  ) { }

  ngOnInit(): void {
    const shopIdParams = this.route.snapshot.paramMap.get('id');
    if (shopIdParams !== null) {
      this.shopId = +shopIdParams;
      this.fetchShopData();
    }
    
  }

  ngAfterViewInit(): void {
    this.initializeMap();
  }

  fetchShopData(): void {
    this.shopService.getShop(this.shopId).subscribe(shop => {
      this.shop = shop;
      this.gameItems = this.shop.items.map((item: any) => item.game);
      console.log(this.gameItems);
    });
  }


  initializeMap(): void {
    const mapElement = document.getElementById('map')!;
    this.map = L.map(mapElement).setView([this.shop.address.longitude, this.shop.address.latitude], 13
      );

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(this.map);

    L.marker([this.shop.address.longitude, this.shop.address.latitude]).addTo(this.map)
      .bindPopup(this.shop.name)
      .openPopup();
  }

  buy(id : any) {

  }
  public addToWishList(id : any): void {
    this.wishListService.addToWishList(id).subscribe()
  }

  public gameDetails(id : any): void {
    this.router.navigate([`games/${id}`]);
  }
}
