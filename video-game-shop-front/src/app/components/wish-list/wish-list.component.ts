import { Component, OnInit } from '@angular/core';
import { WishListService } from 'src/app/services/wish-list.service';

@Component({
  selector: 'app-wish-list',
  templateUrl: './wish-list.component.html',
  styleUrls: ['./wish-list.component.css']
})
export class WishListComponent implements OnInit {

  products : any;

  constructor(private wishListService : WishListService) { }

  ngOnInit(): void {
    this.getAllWishListProducts();
  }

  private getAllWishListProducts(): void {
    this.wishListService.getAllProducts().subscribe(data => {
      this.products = data;
    }, error => {
      alert("Error");
    })
  }

  public removeProductFromWishList(id : any) {
    console.log(id)
    this.wishListService.removeProduct(id).subscribe(data => {
      this.products = data;
    })
  }
}
