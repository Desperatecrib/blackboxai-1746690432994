import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  news = [
    { title: 'Welcome to Sacyl Conecta', content: 'Latest news and updates will appear here.' },
    { title: 'COVID-19 Vaccination Drive', content: 'Vaccination schedules and information.' }
  ];
}
