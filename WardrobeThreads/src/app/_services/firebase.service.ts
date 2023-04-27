import { ConditionalExpr } from '@angular/compiler';
import { Component, inject, Injectable } from '@angular/core';
import {
  Database,
  set,
  ref,
  update,
  onValue,
  get,
  push,
  remove
} from '@angular/fire/database';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class FirebaseService {

  constructor(public database: Database, private router: Router) { }

  setAdmin(userId: any, admin: boolean) {
    update(ref(this.database, 'users/' + userId), {
      isAdmin: admin,
    });
  }

  async checkIfAdmin(userId: any) {
    var user: any;

    const snapshot = await get(ref(this.database, '/users/' + userId));
    user = snapshot.val();
    return user.isAdmin;
  }
}
