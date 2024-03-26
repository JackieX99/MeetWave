import { Injectable } from '@angular/core';
import { EnvironmentService } from './environment.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LocalStorageService } from './local-storage.service';
import { Router } from '@angular/router';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private _env: EnvironmentService,
    private http: HttpClient,
    private localStorage: LocalStorageService,
    private router: Router,
    private us: UserService
  ) {}

  logout(){
    this.localStorage.clear();
    this.us.clearUser();
    this.router.navigate(["/login"]);
  }

  private url = this._env.getVtmUrl();

  private _RegisterUserLink = this.url + '/auth/register';
  private _LoginUserLink = this.url + '/auth/login';
  private _getUserDataByTokenLink = this.url + '/auth/getUserData';

  registerUser(body: any) {
    return this.http.post<any>(this._RegisterUserLink, body);
  }

  loginUser(body: any) {
    return this.http.post<any>(this._LoginUserLink, body);
  }

  getUserData(token: string) {
    console.log(token)
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });
  
    return this.http.get<any>(this._getUserDataByTokenLink, {
      headers: headers,
    });
  }
}
