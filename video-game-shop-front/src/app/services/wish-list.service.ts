import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WishListService {

  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public getAllProducts(): Observable<any> {
    return this.http.get(this.baseUrl + 'api/wish-list');
  }

  public removeProduct(id : any): Observable<any> {
    return this.http.delete(this.baseUrl + `api/wish-list/${id}`);
  }

  public addToWishList(id : any): Observable<any> {
    return this.http.post(this.baseUrl + `api/wish-list`, {productId : id});
  }
}
