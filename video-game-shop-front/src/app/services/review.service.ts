import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public writeReview(body: any): Observable<any> {
    return this.http.post(this.baseUrl + `api/review`, body);
  }

  public getUserReviews(page: any,): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 10);
    return this.http.get(this.baseUrl + `api/review/user`, { params: queryParams });
  }

  public getGameReviews(id: any): Observable<any> {
    return this.http.get(this.baseUrl + `api/review/game/${id}`);
  }

  public getReviewsForApproval(): Observable<any> {
    return this.http.get(this.baseUrl + `api/review`);
  }

  public approve(id: any): Observable<any> {
    return this.http.put(this.baseUrl + `api/review/approve/${id}`, {});
  }

  public decline(id: any): Observable<any> {
    return this.http.put(this.baseUrl + `api/review/deny/${id}`, {});
  }

  public delete(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + `api/review/${id}`);
  }

}
