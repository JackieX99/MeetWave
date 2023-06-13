import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-purchase-completing',
  templateUrl: './purchase-completing.component.html',
  styleUrls: ['./purchase-completing.component.scss']
})
export class PurchaseCompletingComponent {
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  thirdFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });

  isLinear = false;

  constructor(private _formBuilder: FormBuilder) {}
}
