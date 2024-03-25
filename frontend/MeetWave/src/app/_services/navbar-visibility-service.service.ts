import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavbarVisibilityService {

  private _visible = true;

  constructor() { }

  get visible(): boolean {
    return this._visible;
  }

  set visible(value: boolean) {
    this._visible = value;
  }
}
