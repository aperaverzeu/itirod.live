import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUser, faHouse } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  faUser = faUser
  faHouse = faHouse
  burgerOpen: boolean = false
  
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  navClick(link: string) {
    this.router.navigate([link])
    this.burgerOpen = false;
  }

}
