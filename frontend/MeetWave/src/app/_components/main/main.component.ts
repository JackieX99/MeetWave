import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { LocalStorageService } from 'src/app/_services/local-storage.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements AfterViewInit {

  @ViewChild('targetGyik') targetGyikElement!: ElementRef;
  
  panelOpenState = false;

  constructor(private localStorage: LocalStorageService) {}

  ngAfterViewInit(): void {
    let routeto: String = this.localStorage.getItem("routeTo");
    this.localStorage.removeItem("routeTo");
    this.navigateToTarget(routeto)
  }

  public navigateToTarget(target: String){
    switch(target){
      case "gyik":
        this.targetGyikElement.nativeElement.scrollIntoView({ behavior: 'smooth' });
        break;
      default:
        // do nothing
    }
  }
}
