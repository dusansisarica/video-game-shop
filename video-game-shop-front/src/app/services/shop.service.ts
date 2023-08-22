import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public getShop(id : any): Observable<any> {
    return this.http.get(this.baseUrl + `api/shop/${id}`);
  }


}
