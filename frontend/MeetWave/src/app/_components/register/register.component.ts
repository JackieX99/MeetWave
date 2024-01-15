import { Component, ElementRef, Renderer2 } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Observable, Subscription, map } from 'rxjs';
import { AuthService } from 'src/app/_services/auth.service';
import { SnackbarService } from '../_shared/snackbar/snackbar.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  private show = false;
  private message: string = 'This is a snackbar';
  private type: string = 'success';

  profileForm = new FormGroup({
    fullName: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    passwordRe: new FormControl('', Validators.required),
    phone: new FormControl('', Validators.required),
  });

  constructor(
    private el: ElementRef,
    private renderer: Renderer2,
    private auth: AuthService,
    private snackbarService: SnackbarService
  ) {}

  regUser() {
    console.log('katt');
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
            console.log(res)
          });
        }
      } else {
        this.snackbarService.show('nem passzolnak a jelszavak', 'danger');
      }
    } else {
      this.snackbarService.show('mindent ki kéne tölteni', 'danger');
    }
  }

  formClicked() {
    // const logoElement = this.el.nativeElement.querySelector('.logo');
    // const formElement = this.el.nativeElement.querySelector('.form');
    // this.renderer.addClass(logoElement, 'moveLogo');
    // this.renderer.addClass(formElement, 'formBackground');
  }
}
