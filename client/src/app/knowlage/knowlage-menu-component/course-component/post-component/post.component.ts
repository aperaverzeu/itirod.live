import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from 'src/app/shared/post.model';
import { PostService } from 'src/app/shared/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  post: Post = <Post>{}
  title: string = ''

  constructor(private service: PostService, private router: Router) { 
    const route = this.router.url.split('/');
    this.title = route[route.length - 1];
    this.service.getPostByTitle(this.title)
      .subscribe(data => {
        this.post = data;
      })
  }

  ngOnInit() {
  }

}
