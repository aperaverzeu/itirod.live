import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';
import { Course } from 'src/app/shared/course.model';
import { PostService } from 'src/app/shared/post.service';
import { Post } from '../../post.model';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  course: Course = <Course>{}
  cssClass: string = ''

  constructor(private post: PostService, private router: Router) { 
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state as {
      course: Course,
      cssClass: string
    };
    this.course = state.course;
    this.cssClass = state.cssClass;
  }

  selectPost(post: Post) {
    const navigationExtras: NavigationExtras = {
      state: {
        post: post,
        cssClass: this.cssClass
      }
    };
    this.router.navigate([`knowlage/${this.course.title}/${post.title}`], navigationExtras);
  }

  ngOnInit() {
    
  }

}
