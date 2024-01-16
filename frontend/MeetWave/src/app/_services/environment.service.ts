import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  constructor() { }

  url = "https://api.cherrylands.hu";

  getVtmUrl(): String {
    return this.url;
  }
}
