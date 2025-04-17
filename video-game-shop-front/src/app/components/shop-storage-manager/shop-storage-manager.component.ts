import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup } from '@angular/forms';
import { ShopService } from 'src/app/services/shop.service';

@Component({
  selector: 'app-shop-storage-manager',
  templateUrl: './shop-storage-manager.component.html',
  styleUrls: ['./shop-storage-manager.component.css']
})
export class ShopStorageManagerComponent implements OnInit {

  shops!: any[]
  games!: any[]
  selectedShop = 1
  validateForm!: UntypedFormGroup;
  totalNumber: any
  isVisible = false;


  constructor(private shopService: ShopService) {

  }

  ngOnInit(): void {
    this.getShops();
  }

  getShops() {
    this.shopService.getAll(1).subscribe(data => {
      this.shops = data.shops;
      this.fetchGames(1);
    })

  }

  getGames(shop: any) {
    // this.selectedShop = shop.target.value;
    console.log(this.selectedShop)
    this.fetchGames(1)
  }

  addGame() {
    this.isVisible = true;
  }

  handleCancel() {
    this.isVisible = false;
  }



  fetchGames(page: any) {
    console.log(page)
    this.shopService.getGames(this.selectedShop, page).subscribe(shop => {
      this.games = shop.gameQuantities;
      this.totalNumber = shop.totalNumber;
      console.log(this.totalNumber);

      console.log(this.games);
    });
  }


}
