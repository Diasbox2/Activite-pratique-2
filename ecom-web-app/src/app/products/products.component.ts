import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // Required for *ngIf and *ngFor

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {

  public products: any;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    // This URL goes to the Gateway (8888), which forwards to Inventory Service
    this.http.get("http://localhost:8888/inventoryy-service/api/products").subscribe({
      next: (data) => {
        console.log("SUCCESS: Data received from Backend:", data); // Check your console for this!
        this.products = data;
      },
      error: (err) => {
        console.error("ERROR: Could not fetch data:", err);
      }
    });
  }
}
