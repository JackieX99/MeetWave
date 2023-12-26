import { BreakpointObserver } from '@angular/cdk/layout';
import { StepperOrientation } from '@angular/cdk/stepper';
import { Component, ElementRef, Renderer2 } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { Observable, map } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
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
