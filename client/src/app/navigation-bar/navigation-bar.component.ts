import { Component, OnInit } from '@angular/core';
import { faUser, faHouse } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  faUser = faUser
  faHouse = faHouse
  
  constructor() { }

  ngOnInit(): void {
  }

}
