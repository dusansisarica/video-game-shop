import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class FeaturedGamesService {

  private baseUrl = environment.baseUrl;
  constructor(private http: HttpClient) { }

  public getAllGames(page: any): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 10);

    return this.http.get(this.baseUrl + 'api/games', { params: queryParams });
  }

  public getAllGamesGenres(page: any, genres: String[], platforms: String[]): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 12);
    queryParams = queryParams.append('genres', genres.join(','));
    queryParams = queryParams.append('platforms', platforms.join(','));

    return this.http.get(this.baseUrl + 'api/games', { params: queryParams });
  }

  public getAllWithParams(page: any, genres: String[], platforms: String[], searchQuery: string, sortBy: string, sortDirection: string): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 12);
    queryParams = queryParams.append('genres', genres.join(','));
    queryParams = queryParams.append('platforms', platforms.join(','));
    queryParams = queryParams.append('searchQuery', searchQuery);
    queryParams = queryParams.append('sortBy', sortBy);
    queryParams = queryParams.append('sortDirection', sortDirection);



    return this.http.get(this.baseUrl + 'api/games', { params: queryParams });
  }

  public searchGames(page: any, searchQuery: string): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", page);
    queryParams = queryParams.append("size", 12);
    queryParams = queryParams.append('searchQuery', searchQuery);

    return this.http.get(this.baseUrl + 'api/games', { params: queryParams });
  }

  public getGenres(): Observable<any> {
    return this.http.get(this.baseUrl + 'api/games/genres');
  }

  public getPlatforms(): Observable<any> {
    return this.http.get(this.baseUrl + 'api/games/platforms');
  }


  public getFeaturedGames(): Observable<any> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("page", 1);
    queryParams = queryParams.append("size", 9);

    return this.http.get(this.baseUrl + 'api/games/featured', { params: queryParams });
  }

  public getGameById(id: any): Observable<any> {
    return this.http.get(this.baseUrl + `api/games/${id}`);
  }

  public addNewGame(body: any): Observable<any> {
    return this.http.post(this.baseUrl + 'api/games', body);
  }

  public updateGame(body: any): Observable<any> {
    return this.http.put(this.baseUrl + 'api/games', body);
  }

  public deleteGame(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + `api/games/${id}`);
  }

  public getTotalQuantity(id: any): Observable<any> {
    return this.http.get(this.baseUrl + `api/games/quantity/${id}`);
  }

  public getRecommendedGames(recentIds: number[]): Observable<any[]> {
    return this.http.post<any[]>(this.baseUrl + 'api/games/recommended', recentIds);
  }


}
