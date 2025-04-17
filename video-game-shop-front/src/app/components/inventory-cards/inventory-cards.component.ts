import { Component, OnInit } from '@angular/core';
import { ShopService } from 'src/app/services/shop.service';

@Component({
  selector: 'app-inventory-cards',
  templateUrl: './inventory-cards.component.html',
  styleUrls: ['./inventory-cards.component.css']
})
export class InventoryCardsComponent implements OnInit {

  items!: any[];
  totalNumber!: any;
  inputValue: string = "";

  constructor(private shopService: ShopService) { }

  ngOnInit(): void {
    this.getItems();
  }

  getItems() {
    this.shopService.getShopItems(1, "").subscribe(data => {
      this.items = data.gameQuantities;
      console.log(this.items)
      this.totalNumber = data.totalNumber;
    })
  }

  getSearch() {
    this.getData(1);
  }



  getData(page: any) {
    this.shopService.getShopItems(page, this.inputValue).subscribe(data => {
      this.items = data.gameQuantities;
      this.totalNumber = data.totalNumber;
    })
  }

}
