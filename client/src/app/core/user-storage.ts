import {Injectable} from '@angular/core';
import { User } from './user.model';

const USER_KEY = 'username';

@Injectable()
export class UsernameStorage {

  constructor() {
  }

  set(username: string) {
    window.localStorage[USER_KEY] = username;
  }

  get() {
    return window.localStorage[USER_KEY];
  }

  destroy() {
    window.localStorage.removeItem(USER_KEY);
  }

}
