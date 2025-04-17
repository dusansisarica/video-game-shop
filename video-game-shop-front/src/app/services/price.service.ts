import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PriceService {
  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public makePrice(price: any, gameId: any): Observable<any> {
    const body = {
      price: price.price,
      startDate: price.startDate,
      endDate: price.endDate,
      action: {
        name: price.action.name,
        actionType: price.action.actionType,
        discountValue: price.action.discountValue,
        startDate: price.action.startDate,
        endDate: price.action.endDate,
      }
    };
    return this.http.post(this.baseUrl + `api/price/${gameId}`, body);
  }

}
