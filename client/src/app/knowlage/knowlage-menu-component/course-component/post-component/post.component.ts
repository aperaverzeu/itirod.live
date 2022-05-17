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
  cssClass: string = ''

  constructor(private service: PostService, private router: Router) { 
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state as {
      post: Post,
      cssClass: string
    };
    this.post = state.post;
    this.cssClass = state.cssClass;
    console.log(state)
  }

  ngOnInit() {
  }

}
