import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public makeOrder(): Observable<any> {
    return this.http.post(this.baseUrl + `api/order`, {});
  }

  public getUsersOrders(): Observable<any> {
    return this.http.get(this.baseUrl + `api/order`);
  }

  public getAll(): Observable<any> {
    return this.http.get(this.baseUrl + `api/order/all`);
  }

  public ship(body : any): Observable<any> {
    return this.http.put(this.baseUrl + `api/order/ship`, body);
  }


}
