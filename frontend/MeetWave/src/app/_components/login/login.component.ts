import { Component, ElementRef, Renderer2 } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/_services/auth.service';
import { SnackbarService } from '../_shared/snackbar/snackbar.service';
import { LocalStorageService } from 'src/app/_services/local-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  profileForm = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  constructor(
    private auth: AuthService,
    private snackbarService: SnackbarService,
    private localStorageService: LocalStorageService,
    private router: Router
    ) {}

  logUser() {
    if(this.profileForm.valid) {
      this.auth.loginUser({
        "email": this.profileForm.value.email,
        "password": this.profileForm.value.password
    }).subscribe((res: any) => {
      if(res.status == "success"){
        this.localStorageService.setItem("token", res.token);
        this.snackbarService.show('Sikeres bejelentkezés.');
        setTimeout(() => {
          this.router.navigate(["/dashboard"]);
        }, 2000);
      }
    });
    } else{
      this.snackbarService.show('mindent ki kéne tölteni', 'danger');
    }
  }

}
