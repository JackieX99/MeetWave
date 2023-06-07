import { EventEmitter, Injectable, Optional } from '@angular/core';
import { Auth, User, createUserWithEmailAndPassword, getAuth, onAuthStateChanged, signInWithEmailAndPassword, signOut, updateProfile } from '@angular/fire/auth';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  token: any = null;

  user: any;

  currentUser: any;
  navbarName: any;

  public changedUser: EventEmitter<any> = new EventEmitter<any>();
  public userStillLoggedIn: EventEmitter<any> = new EventEmitter<any>();

  public userChanged() {
    this.changedUser.emit();
  }
  public userFound() {
    this.userStillLoggedIn.emit();
  }

  constructor(private router: Router, @Optional() private auth: Auth) {}

  signInUser(email: string, password: string) {

    signInWithEmailAndPassword(this.auth, email, password)
      .then((response) => {
        this.currentUser = this.auth.currentUser;
        this.navbarName = this.currentUser.displayName.split(' ')[0];
        this.router.navigate(['/main']);
        this.token = this.auth.currentUser?.getIdToken();
        this.userChanged();
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
        this.userChanged();
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

  setUser(user: any){
    this.currentUser = user;
    this.navbarName = this.currentUser.displayName.split(' ')[0];
  }

  getNavbarName(){
    return this.navbarName;
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
