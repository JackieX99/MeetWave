import { AfterViewInit, Component, Input, OnInit, Output } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { SearchbarService } from 'src/app/_services/searchbar.service';
import { getAuth, onAuthStateChanged } from "firebase/auth";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {

  @Input() activeRoute: any = "";
  name: any;
  currentUser: any;

  constructor(private sb: SearchbarService, private authS: AuthService) {}
  
  ngOnInit(): void {
    this.refreshName()
    this.authS.changedUser.subscribe((data) => { 
      this.refreshName();
    });
    this.authS.userStillLoggedIn.subscribe((data) => { 
      this.refreshName();
    });
  }

  refreshName(){
    if(this.authS.getNavbarName() == undefined){
      this.name = "vendég"; 
    } else{
      this.name = this.authS.getNavbarName();
    }
  }

  search(event: any) {
    this.sb.searchFor(event.target.value);
  }
}
