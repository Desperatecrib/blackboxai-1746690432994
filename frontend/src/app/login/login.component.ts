import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';
  registrationId = '';
  isRegistering = false;
  errorMessage = '';

  constructor(private http: HttpClient, private router: Router) {}

  toggleRegister() {
    this.isRegistering = !this.isRegistering;
    this.errorMessage = '';
  }

  login() {
    this.errorMessage = '';
    this.http.post('/api/users/login', { username: this.username, password: this.password })
      .subscribe({
        next: (res: any) => {
          if (res.role === 'DOCTOR') {
            this.router.navigate(['/doctor-dashboard']);
          } else {
            this.router.navigate(['/patient-dashboard']);
          }
        },
        error: () => {
          this.errorMessage = 'Invalid username or password';
        }
      });
  }

  register() {
    this.errorMessage = '';
    this.http.post('/api/users/register', { username: this.username, password: this.password, registrationId: this.registrationId, role: 'PATIENT' })
      .subscribe({
        next: () => {
          this.isRegistering = false;
          this.login();
        },
        error: (err) => {
          this.errorMessage = err.error || 'Registration failed';
        }
      });
  }
}
