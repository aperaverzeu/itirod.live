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
  classes: string[] = ['card1', 'card2', 'card3', 'card4']

  constructor(private post: PostService, private router: Router) { 
    this.post.getCourses()
      .subscribe(data => {
        this.courses = data;
      })
    
  }

  getCssClass(index: number): string {
    return this.classes[index % this.classes.length]
  }

  selectCourse(course: Course, index: number) {
    const navigationExtras: NavigationExtras = {
      state: {
        course: course,
        cssClass: this.getCssClass(index)
      }
    };
    this.router.navigate([`knowlage/${course.title}`], navigationExtras);
  }

  ngOnInit() {
    
  }
}
