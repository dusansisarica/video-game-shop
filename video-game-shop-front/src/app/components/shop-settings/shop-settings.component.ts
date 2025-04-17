import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { ShopCardsComponent } from '../shop-cards/shop-cards.component';

@Component({
  selector: 'app-shop-settings',
  templateUrl: './shop-settings.component.html',
  styleUrls: ['./shop-settings.component.css']
})
export class ShopSettingsComponent implements OnInit {

  isVisible = false;
  isVisibleAdd = false;
  isVisibleUpdate = false;
  shopId: any;
  inputValue: string = "";
  @ViewChild(ShopCardsComponent) shop!: ShopCardsComponent;

  constructor() { }

  ngOnInit(): void {
  }

  getSearch() {
    this.getData(1);
  }

  getData(page: any) {
    this.shop.getAllShopsSearch(page, this.inputValue);
  }
  addGame(id: any) {
    this.shopId = id;
    console.log(this.shopId)
    this.showModal();
  }

  showModal() {
    this.isVisible = true;
  }

  handleCancel() {
    this.isVisible = false;
  }

  handleOk() {
    this.isVisible = false;
  }

  showModalAdd() {
    this.isVisibleAdd = true;
  }

  handleCancelAdd() {
    this.isVisibleAdd = false;
  }

  handleOkAdd() {
    this.isVisibleAdd = false;
  }

  refreshGames() {
    console.log("TRIGGERED")
    this.shop.ngOnInit();
  }

  updateShop(id: any) {
    this.shopId = id;
    this.isVisibleUpdate = true;
  }

  showModalUpdate() {
    this.isVisibleUpdate = true;
  }

  handleCancelUpdate() {
    this.isVisibleUpdate = false;
  }

  handleOkUpdate() {
    this.isVisibleUpdate = false;
  }


}
