import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-customers',
  standalone: false,
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent {
  customers: any;
  constructor(private http: HttpClient) { }
ngOnInit(): void {
  this.http.get("http://localhost:8888/customer-service/api/customers").subscribe({
    next: (data) => {
      console.log("SUCCESS: Data received from Backend:", data); // Check your console for this!
      this.customers = data;
    },
    error: (err) => {
      console.error("ERROR: Could not fetch data:", err);
    }
  });
}
}
