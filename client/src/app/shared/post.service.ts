import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Post } from './post.model';

@Injectable()
export class PostService {

  private courseUrl = environment.baseApiUrl + '/courses';
  private postUrl = environment.baseApiUrl + '/posts';

  constructor(private http: HttpClient) {}

  getCourses(): Observable<any> {
    return this.http.get(`${this.courseUrl}`);
  }

  getCourseByTitle(title: string): Observable<any> {
    return this.http.get(`${this.courseUrl}/${title}`);
  }

  getPosts(): Observable<any> {
    return this.http.get(`${this.postUrl}`);
  }
  
  getPost(id: string): Observable<any> {
    return this.http.get(`${this.postUrl}/${id}`);
  }

  savePost(data: Post): Observable<any> {
    console.log('saving post:' + data);
    return this.http.post(`${this.postUrl}`, data);
  }

  updatePost(id: string, data: Post): Observable<any> {
    console.log('updating post:' + data);
    return this.http.put(`${this.postUrl}/${id}`, data);
  }

  deletePost(id: string): Observable<any> {
    console.log('delete post by id:' + id);
    return this.http.delete(`${this.postUrl}/${id}`);
  }

}
