import { Component } from '@angular/core';
import { SearchbarService } from 'src/app/_services/searchbar.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {

  constructor(private sb: SearchbarService) {
    
  }
  
  search(event: any) {
    console.log('You entered: ', event.target.value);
    this.sb.searchFor(event.target.value);
  }
}
