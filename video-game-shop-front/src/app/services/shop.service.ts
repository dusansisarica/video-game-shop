import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public getShop(id: any): Observable<any> {
    return this.http.get(this.baseUrl + `api/shop/${id}`);
  }

  public getAll(page: any): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 10);
    // queryParams = queryParams.append("searchQuery", searchQuery);

    return this.http.get(this.baseUrl + `api/shop`, { params: queryParams });
  }

  getAllSearch(page: any, searchQuery: string): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 10);
    queryParams = queryParams.append("searchQuery", searchQuery);

    return this.http.get(this.baseUrl + `api/shop`, { params: queryParams });

  }


  public addGame(body: any): Observable<any> {
    return this.http.post(this.baseUrl + `api/shop/game`, body);
  }
  public addShop(body: any): Observable<any> {
    return this.http.post(this.baseUrl + `api/shop`, body);
  }

  public updateShop(body: any): Observable<any> {
    return this.http.put(this.baseUrl + `api/shop`, body);
  }

  public deleteShop(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + `api/shop/${id}`);
  }

  public getShopItems(page: any, searchQuery: string): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 10);
    queryParams = queryParams.append("searchQuery", searchQuery);

    return this.http.get(this.baseUrl + `api/shop/items`, { params: queryParams });
  }

  public getOrdersShop(): Observable<any> {
    return this.http.get(this.baseUrl + `api/shop/orders`);
  }

  public accept(id: any): Observable<any> {
    return this.http.put(this.baseUrl + `api/shop/orders/accept/${id}`, {});
  }

  public decline(id: any): Observable<any> {
    return this.http.put(this.baseUrl + `api/shop/orders/decline/${id}`, {});
  }

  public ship(id: any): Observable<any> {
    return this.http.put(this.baseUrl + `api/shop/orders/ship/${id}`, {});
  }

  public getGames(id: any, page: any): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 10);
    return this.http.get(this.baseUrl + `api/shop/games/${id}`, { params: queryParams });

  }

  public getShopsForGame(id: any): Observable<any> {
    return this.http.get(this.baseUrl + `api/shop/game/${id}`);
  }



}
