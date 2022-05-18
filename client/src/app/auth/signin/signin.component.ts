import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../core/auth.service';
import {Router} from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserDTO } from 'src/app/core/user.model';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {

  form: FormGroup = <FormGroup>{};

  constructor(private auth: AuthService, private router: Router, private readonly fb: FormBuilder) {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    if (this.form.valid) {
        this.auth.login({username: this.form.value.username, password: this.form.value.password})
        .subscribe(
          (data) => {
            this.router.navigate(['']);
          }
        );
    } else {
        console.log('There is a problem with the form');
    }
  }

  register() {
    this.router.navigate(['/auth/signup']);
  }
}
