import { Injectable } from '@angular/core';
import { EnvironmentService } from './environment.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private _env: EnvironmentService,
    private http: HttpClient,
    ) { }

  private url = this._env.getVtmUrl()

  private _RegisterUserLink = this.url + "/CustomDashboardLink/addCustomDashboardLink";

  registerUser(body: any) {
    return this.http.post<any>(this._RegisterUserLink, body);
  }


}
