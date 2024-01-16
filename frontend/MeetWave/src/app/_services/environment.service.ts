import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  constructor() { }

  url = "https://api.meetwave.hu";

  getVtmUrl(): String {
    return this.url;
  }
}
