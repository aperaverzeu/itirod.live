import {Injectable} from '@angular/core';
import { User } from './user.model';

const USER_KEY = 'user';

@Injectable()
export class UserStorage {

  constructor() {
  }

  set(user: User) {
    const userString = JSON.stringify(user)
    window.localStorage[USER_KEY] = userString;
  }

  get() {
    const userString =  window.localStorage[USER_KEY];
    if (userString) 
      return JSON.parse(userString)
    else 
      return false
  }

  destroy() {
    window.localStorage.removeItem(USER_KEY);
  }

}
