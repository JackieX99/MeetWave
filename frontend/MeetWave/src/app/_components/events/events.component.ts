import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss'],
})
export class EventsComponent {

  showLoadingMore: boolean = false;

  events: any[] = [
    {
      "eventid": 1,
      "date": "Szo, Febr. 3., 22:00-kor",
      "title": "❄️ WINTER COLOR SHOW ❄️ //UV EDITION // [2024.02.03.] ❯❯❯ PÉCS - EXPO",
      "imageSrc": "./assets/images/template-event-bg.jpg",
      "place": "EXPOCenter PÉCS - Pécs",
      "interested": 759,
      "going": 126
    },
    {
      "eventid": 1,
      "date": "Szo, Febr. 3., 22:00-kor",
      "title": "❄️ WINTER COLOR SHOW ❄️ //UV EDITION // [2024.02.03.] ❯❯❯ PÉCS - EXPO",
      "imageSrc": "./assets/images/template-event-bg.jpg",
      "place": "EXPOCenter PÉCS - Pécs",
      "interested": 759,
      "going": 126
    },
    {
      "eventid": 1,
      "date": "Szo, Febr. 3., 22:00-kor",
      "title": "❄️ WINTER COLOR SHOW ❄️ //UV EDITION // [2024.02.03.] ❯❯❯ PÉCS - EXPO",
      "imageSrc": "./assets/images/template-event-bg.jpg",
      "place": "EXPOCenter PÉCS - Pécs",
      "interested": 759,
      "going": 126
    }
  ];

  @HostListener('window:scroll', [])
  onScroll(): void {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
      this.showLoadingMore = true;
      this.events.push({
        "eventid": 1,
        "date": "Szo, Febr. 3., 22:00-kor",
        "title": "❄️ WINTER SZÍN SHOW ❄️ //UV EDITION // [2024.02.03.] ❯❯❯ PÉCS - EXPO",
        "imageSrc": "./assets/images/template-event-bg.jpg",
        "place": "EXPOCenter PÉCS - Pécs",
        "interested": 759,
        "going": 126
      })
      this.showLoadingMore = false;
    }
  }
}
