import { Injectable, Optional } from '@angular/core';
import { Auth, createUserWithEmailAndPassword, signInWithEmailAndPassword, signOut, updateProfile } from '@angular/fire/auth';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  token: any = null;

  currentUser: any;

  constructor(private router: Router, @Optional() private auth: Auth) { }

  signInUser(email: string, password: string) {

    signInWithEmailAndPassword(this.auth, email, password)
      .then((response) => {
        this.currentUser = this.auth.currentUser;
        this.router.navigate(['/main']);
        this.token = this.auth.currentUser?.getIdToken();
      })
      .catch((error) => {
        document.querySelector('.error')!.innerHTML = error.message;
      });
  }

  signUpUser(email: string, password: string, displayname: string) {
    createUserWithEmailAndPassword(this.auth, email, password)
      .then((res: any) => {
        this.currentUser = this.auth.currentUser;
        this.router.navigate(['/main']);
        this.token = this.auth.currentUser?.getIdToken();
        updateProfile(this.currentUser, {displayName: displayname}).catch(
          (error: any) => {
            document.querySelector('.error')!.innerHTML = error.message;
          }
        )
      })
      .catch((error: any) => {
        document.querySelector('.error')!.innerHTML = error.message;
      })
  }


  logout() {
    this.auth.signOut();
    this.token = null;
    this.router.navigate(['/main']);
  }

  getCurrentUser() {
    return this.currentUser;
  }

  getCurrentUserId(){
    return this.currentUser.uid;
  }

  getToken() {
    this.token = this.auth.currentUser?.getIdToken();
    return this.token;
  }

  isAuthenticated() {
    return this.token != null;
  }
}
