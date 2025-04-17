import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public getCart(): Observable<any> {
    return this.http.get(this.baseUrl + `api/cart`);
  }

  public addToCart(item : any): Observable<any> {
    return this.http.post(this.baseUrl + `api/cart`, item);
  }

  public removeItem(id : any): Observable<any> {
    return this.http.delete(this.baseUrl + `api/cart/${id}`);
  }

}
