import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public getLoggedInUser(): Observable<any> {
    return this.http.get(this.baseUrl + 'api/user');
  }

  public getAllUsers(page: any): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 10);

    return this.http.get(this.baseUrl + 'api/user/all', { params: queryParams });
  }

  public getAllUsersSearch(page: any, searchQuery: string): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 10);
    queryParams = queryParams.append("searchQuery", searchQuery);


    return this.http.get(this.baseUrl + 'api/user/all', { params: queryParams });
  }


  public changeUserDetails(body: any): Observable<any> {
    return this.http.put(this.baseUrl + 'api/user', body);
  }

  public employUser(body: any): Observable<any> {
    return this.http.put(this.baseUrl + 'api/user/employ', body);
  }

  public unemployUser(body: any): Observable<any> {
    return this.http.put(this.baseUrl + 'api/user/unemploy', body);
  }

  public getAllRoleUser(page: any, searchQuery: string): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 10);
    queryParams = queryParams.append("searchQuery", searchQuery);

    return this.http.get(this.baseUrl + 'api/user/users', { params: queryParams });
  }

  public getAllRoleUserNoSearch(page: any): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 10);

    return this.http.get(this.baseUrl + 'api/user/users', { params: queryParams });
  }


  public registerManager(body: any): Observable<any> {
    return this.http.post(this.baseUrl + 'api/user', body);
  }

  public deleteUser(id: any): Observable<any> {
    return this.http.put(this.baseUrl + `api/user/delete/${id}`, {})
  }
}
