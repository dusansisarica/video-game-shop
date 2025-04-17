import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NzTableSortFn, NzTableSortOrder } from 'ng-zorro-antd/table';
import { ShopService } from 'src/app/services/shop.service';

interface Shop {
  id: number;
  name: string;
  address: {
    address: string,
    city: string,
    country: string
  },
  deleted: boolean,
}

interface ColumnItem {
  name: string;
  sortOrder: NzTableSortOrder | null;
  sortFn: NzTableSortFn<Shop> | true;
  sortDirections: NzTableSortOrder[];
}


@Component({
  selector: 'app-shop-cards',
  templateUrl: './shop-cards.component.html',
  styleUrls: ['./shop-cards.component.css']
})
export class ShopCardsComponent implements OnInit {

  shops!: Shop[];
  totalNumber: any;
  sortDescending = false;
  @Output() dataEvent = new EventEmitter<any>();
  @Output() updateShop = new EventEmitter<any>();

  constructor(private shopService: ShopService) { }
  listOfColumns: ColumnItem[] = [
    {
      name: 'Id',
      sortOrder: 'descend',
      sortFn: (a: Shop, b: Shop) => a.id - b.id,
      sortDirections: ['ascend', 'descend', null]
    },
    {
      name: 'Name',
      sortOrder: 'null',
      sortFn: (a: Shop, b: Shop) => a.name.localeCompare(b.name),
      sortDirections: ['ascend', 'descend', null]
    }
  ]

  ngOnInit(): void {
    this.getAllShops(1);
  }

  getAllShops(page: any) {
    console.log("CHILD TRIGGERED")
    this.shopService.getAll(page).subscribe(data => {
      this.shops = data.shops;
      this.shops.sort(this.idSort);
      this.totalNumber = data.totalNumber;
      console.log(this.shops)
    })
  }

  getAllShopsSearch(page: any, searchQuery: string) {
    this.shopService.getAllSearch(page, searchQuery).subscribe(data => {
      this.shops = data.shops;
      this.shops.sort(this.idSort);
      this.totalNumber = data.totalNumber;
      console.log(this.shops)
    })
  }



  addGame(id: any) {
    this.dataEvent.emit(id);
  }

  update(id: any) {
    this.updateShop.emit(id);
  }

  getData(page: any) {
    console.log(page);
    this.shopService.getAll(page).subscribe(data => {
      this.shops = data.shops;
      this.shops.sort(this.idSort);
      this.totalNumber = data.totalNumber;
    })
  }

  sortById() {
    console.log("SORT")
    this.shops.reverse();
  }

  sortKey: string | null = null;
  sortValue: NzTableSortOrder | null = null;

  // Sorting functions
  idSort = (a: any, b: any) => {
    // Type assertion (assuming 'id' is a number)
    const idA = a.id as number;
    const idB = b.id as number;
    return idA - idB;
  };

}
