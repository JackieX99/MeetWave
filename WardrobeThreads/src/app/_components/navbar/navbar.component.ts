import { Component, Input, OnInit, Output } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { SearchbarService } from 'src/app/_services/searchbar.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {

  @Input() activeRoute: any = "";
  name: any;

  constructor(private sb: SearchbarService, private authS: AuthService) {
    
  }
  
  ngOnInit(): void {
    this.authS.getNavbarName().then((res: any) => {
      if(res.length == ""){
        this.name = "vendég"
      } else{
        this.name = res;
      }
    })
  }

  search(event: any) {
    console.log('You entered: ', event.target.value);
    this.sb.searchFor(event.target.value);
  }
}
