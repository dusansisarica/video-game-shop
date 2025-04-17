import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {
  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public addDiscount(discount: any, id: any): Observable<any> {
    return this.http.post(this.baseUrl + `api/discount/${id}`, discount);
  }

}
