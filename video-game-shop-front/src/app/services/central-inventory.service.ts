import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CentralInventoryService {

  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public getItems(page: any): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 10);

    return this.http.get(this.baseUrl + `api/central-inventory`, { params: queryParams });
  }
}
