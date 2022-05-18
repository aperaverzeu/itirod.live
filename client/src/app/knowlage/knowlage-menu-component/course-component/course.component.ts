import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';
import { Course } from 'src/app/shared/course.model';
import { Post } from 'src/app/shared/post.model';
import { PostService } from 'src/app/shared/post.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  posts: Post[] = []
  title: string = 'title'

  constructor(private service: PostService, private router: Router) { 
    const route = this.router.url.split('/');
    this.title = route[route.length - 1];
    this.service.getPostsByTitle(this.title)
      .subscribe(data => {
        this.posts = data;
      })
  }

  selectPost(post: Post) {
    this.router.navigate([`knowlage/${this.title}/${post.title}`]);
  }

  ngOnInit() {
    
  }

}
