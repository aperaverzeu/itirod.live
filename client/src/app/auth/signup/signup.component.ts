import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../core/auth.service';
import {Router} from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserDTO } from 'src/app/core/user.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  form: FormGroup = <FormGroup>{};

  constructor(private router: Router, private readonly fb: FormBuilder, private auth: AuthService) {
    this.form = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(32)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  register() {  
    if (this.form.valid) {
      const user = <UserDTO>this.form.value;
      this.auth.attempRegister(user)
      .subscribe(
        (data) => {
          this.router.navigate(['']);
        }
      );
  } else {
      console.log('There is a problem with the form');
  }
  }

  login() {
    this.router.navigate(['/auth/signin']);
  }
}
