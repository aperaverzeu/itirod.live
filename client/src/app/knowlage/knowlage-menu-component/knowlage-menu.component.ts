import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';
import { Course } from 'src/app/shared/course.model';
import { Post } from 'src/app/shared/post.model';
import { PostService } from 'src/app/shared/post.service';

@Component({
  selector: 'app-knowlage-menu',
  templateUrl: './knowlage-menu.component.html',
  styleUrls: ['./knowlage-menu.component.css']
})
export class KnowlageMenuComponent implements OnInit {

  courses: Course[] = []

  constructor(private service: PostService, private router: Router) { 
    this.service.getCourses()
      .subscribe(data => {
        this.courses = data;
      })
    
  }

  selectCourse(course: Course) {
    this.router.navigate([`knowlage/${course.title}`]);
  }

  ngOnInit() {
    
  }
}
