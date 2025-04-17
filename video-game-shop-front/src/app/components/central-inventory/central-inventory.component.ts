import { Component, OnInit } from '@angular/core';
import { CentralInventoryService } from 'src/app/services/central-inventory.service';

@Component({
  selector: 'app-central-inventory',
  templateUrl: './central-inventory.component.html',
  styleUrls: ['./central-inventory.component.css']
})
export class CentralInventoryComponent implements OnInit {

  items!: any[];
  totalNumber: any;
  constructor(private centralInventoryService: CentralInventoryService) { }

  ngOnInit(): void {
    this.getItems(1);
  }

  getItems(page: any) {
    this.centralInventoryService.getItems(page).subscribe(data => {
      this.items = data.centralInventory;
      this.totalNumber = data.totalSize;
    })
  }

  getData(page: any) {
    this.getData(page);
  }

}
