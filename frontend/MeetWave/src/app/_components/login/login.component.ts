import { Component, ElementRef, Renderer2 } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

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
    private el: ElementRef,
    private renderer: Renderer2
  ) {}

  formClicked() {
    // const logoElement = this.el.nativeElement.querySelector('.logo');
    // const formElement = this.el.nativeElement.querySelector('.form');
  
    // this.renderer.addClass(logoElement, 'moveLogo');
    // this.renderer.addClass(formElement, 'formBackground');
  }
}
