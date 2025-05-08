import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-doctor-dashboard',
  templateUrl: './doctor-dashboard.component.html',
  styleUrls: ['./doctor-dashboard.component.css']
})
export class DoctorDashboardComponent implements OnInit {
  appointments: any[] = [];
  doctorId = 1; // This should be dynamically set after login

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadAppointments();
  }

  loadAppointments() {
    this.http.get<any[]>(\`/api/appointments/doctor/\${this.doctorId}\`).subscribe(data => {
      this.appointments = data;
    });
  }

  moveUpAppointment(appointmentId: number) {
    this.http.post(\`/api/appointments/move-up/\${appointmentId}\`, {}).subscribe(() => {
      this.loadAppointments();
    });
  }
}
