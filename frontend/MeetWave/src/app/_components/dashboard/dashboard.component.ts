import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { LocalStorageService } from 'src/app/_services/local-storage.service';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  user: any;

  constructor(
    private localStorageService: LocalStorageService,
    private authService: AuthService,
    private userService: UserService
  ){
    this.loadUserData(this.localStorageService.getItem("token"))  
  }

  ngOnInit(): void {
    
  }

  async loadUserData(token: string){
    const userData = await this.authService.getUserData(token).toPromise();
    this.user = userData.userdata[0];
    console.log(this.user)
  }
  
  

  logout(){
    this.authService.logout();
  }

}
