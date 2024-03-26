import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  constructor() { }

  url = "http://localhost:8080";
  // url = "https://api.meetwave.hu";

  getVtmUrl(): String {
    return this.url;
  }
}
