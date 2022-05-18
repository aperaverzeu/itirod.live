import { Component, OnInit } from '@angular/core';
import { Wonder } from '../wonder.model';
import { WonderService } from '../wonder.service';

@Component({
  selector: 'app-wonder',
  templateUrl: './wonder.component.html',
  styleUrls: ['./wonder.component.css']
})
export class WonderComponent implements OnInit {
  wonder: Wonder = <Wonder>{};

  constructor(private service: WonderService) { 
    this.service.getWonder()
      .subscribe(data => {
        this.wonder = data;
      })
  }

  ngOnInit() {
  }
}
