import { Post } from "./post.model";

export interface Course {
  id?: string;
  title: string;
  posts: Post[];
}