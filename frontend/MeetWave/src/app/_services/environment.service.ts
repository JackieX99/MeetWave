import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {

  constructor() { }

  url = "http://46.107.215.7:11110";

  getVtmUrl(): String {
    return this.url;
  }
}