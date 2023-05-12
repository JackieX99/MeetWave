import { Component, OnInit } from '@angular/core';
import { AuthService } from './_services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {

  activeRouter: any = "";

  constructor(private auth: AuthService) {
    
  }

  ngOnInit(): void {
    if (this.auth.isAuthenticated()) {
      //Navigate to Home Page
    } else {
      //Navigate to Login Page
    }
  }
  
  public onRouterOutletActivate(event : any) {
    this.activeRouter = event.constructor.name; 
    // PageNotFoundComponent
}
}
