import { Component, ElementRef, Renderer2 } from '@angular/core';
import {
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { AuthService } from 'src/app/_services/auth.service';
import { SnackbarService } from '../_shared/snackbar/snackbar.service';
import { LocalStorageService } from 'src/app/_services/local-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {

  profileForm = new FormGroup({
    fullName: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    passwordRe: new FormControl('', Validators.required),
    phone: new FormControl('', Validators.required),
  });

  constructor(
    private auth: AuthService,
    private snackbarService: SnackbarService,
    private localStorageService: LocalStorageService,
    private router: Router
  ) {}

  regUser() {
    if (this.profileForm.valid) {
      if (
        this.profileForm.value.password == this.profileForm.value.passwordRe
      ) {
        if (this.profileForm.value.phone?.length == 9) {
          this.auth.registerUser({
            fullNameIN: this.profileForm.value.fullName,
            emailIN: this.profileForm.value.email,
            passwordIN: this.profileForm.value.password,
            phoneNumberIN: this.profileForm.value.phone,
          }).subscribe((res: any) => {
            if(res.status == "success"){
              this.localStorageService.setItem("token", res.token);
              this.snackbarService.show('Sikeres bejelentkezés.');
              setTimeout(() => {
                this.router.navigate(["/dashboard"]);
              }, 2000);
            } else{
              this.snackbarService.show(res.error, 'danger');
            }
          });
        }
      } else {
        this.snackbarService.show('nem passzolnak a jelszavak', 'danger');
      }
    } else {
      this.snackbarService.show('mindent ki kéne tölteni', 'danger');
    }
  }
}
