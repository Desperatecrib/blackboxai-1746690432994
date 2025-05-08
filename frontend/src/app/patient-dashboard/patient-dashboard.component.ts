import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-patient-dashboard',
  templateUrl: './patient-dashboard.component.html',
  styleUrls: ['./patient-dashboard.component.css']
})
export class PatientDashboardComponent implements OnInit {
  notes: any[] = [];
  newNote = '';
  patientId = 1; // This should be dynamically set after login

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadNotes();
  }

  loadNotes() {
    this.http.get<any[]>(`/api/patient-notes/patient/${this.patientId}`).subscribe(data => {
      this.notes = data;
    });
  }

  addNote() {
    if (!this.newNote.trim()) return;
    const note = { patient: { id: this.patientId }, note: this.newNote, createdAt: new Date() };
    this.http.post('/api/patient-notes', note).subscribe(() => {
      this.newNote = '';
      this.loadNotes();
    });
  }
}
