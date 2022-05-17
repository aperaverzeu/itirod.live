export interface User {
  name: string;
  roles?: string[];
}

export interface UserDTO {
  username: string;
  email: string;
  password: string;
}
