import { Username } from "../shared/username.model";

export interface Post {
  id?: string;
  slug?: string;
  title: string;
  content: string;
  author?: Username;
  createdDate?: any;
}

// перенести шаред в ноуледж 