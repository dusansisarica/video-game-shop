import { Component, OnInit } from '@angular/core';
import { CentralInventoryService } from 'src/app/services/central-inventory.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-central-user-orders',
  templateUrl: './central-user-orders.component.html',
  styleUrls: ['./central-user-orders.component.css']
})
export class CentralUserOrdersComponent implements OnInit {

  orders!: any[];
  items!: any[];
  isVisible = false;
  order!: any;
  modalWidth = 800;
  isOkLoading = false;
  constructor(private orderService: OrderService, private centralInventoryService: CentralInventoryService) { }

  ngOnInit(): void {
    this.getOrders();
    this.getItems();
  }

  getOrders() {
    this.orderService.getAll().subscribe(data => {
      this.orders = data;
    })
  }

  getItems() {
    this.centralInventoryService.getItems(1).subscribe(data => {
      this.items = data.centralInventory;
    })
    this.getOrders();
    this.isVisible = false;
  }


  findItem(id: any) {
    let item = this.items.find(item => item.videoGame.ID === id);
    console.log(this.items)
    console.log(id)
    if (item == null) {
      return 0;
    }
    return item.quantity;
  }

  showModal(order: any) {
    this.order = order;
    this.isVisible = true;
  }

  handleCancel() {
    console.log("CANCEL")
    this.isVisible = false;
  }

  handleOk() {
    this.isVisible = false;
  }

  loadingDone() {
    this.isOkLoading = false;
  }

  loadingStart() {
    this.isOkLoading = true
  }


}
