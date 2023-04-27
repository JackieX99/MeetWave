import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { FirebaseService } from 'src/app/_services/firebase.service';

@Component({
  selector: 'app-main-panel',
  templateUrl: './main-panel.component.html',
  styleUrls: ['./main-panel.component.scss']
})
export class MainPanelComponent implements OnInit {

  currentUser: any;
  lastLogin: any;
  regTime: any;
  isAdmin: boolean = false;

  constructor(private readonly authService: AuthService, private readonly firebaseService: FirebaseService) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    this.currentUser? this.calcTime() : "";
    this.firebaseService.checkIfAdmin(this.authService.getCurrentUserId()).then(res =>
      this.isAdmin = res
    )
  }

  calcTime(){
    // var today: any = new Date();
    var lastLog = this.currentUser.metadata.lastLoginAt;
    // var diffMs = (today - lastLog);
    // var diffDays = Math.floor(diffMs / 86400000);
    // var diffHrs = Math.floor((diffMs % 86400000) / 3600000);
    // var diffMins = Math.round(((diffMs % 86400000) % 3600000) / 60000);
    // console.log(diffDays + " napja, " + diffHrs + " órája, " + diffMins + " perce ");

    var date = new Date(+lastLog);
    this.lastLogin = date.toLocaleString();

    var reg = new Date(+this.currentUser.metadata.createdAt);
    this.regTime = reg.toLocaleString();
    
  }

  setAdmin(bool: boolean){
    this.firebaseService.setAdmin(this.authService.getCurrentUserId(), bool);
  }

}
