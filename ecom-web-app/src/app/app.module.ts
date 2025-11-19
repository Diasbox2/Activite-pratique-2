import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; // Enable HTTP requests
import { RouterModule } from '@angular/router'; // Enable Routing

import { AppComponent } from './app.component';
import { routes } from './app.routes'; // Import your routes
import { ProductsComponent } from './products/products.component'; // Import your component

@NgModule({
  declarations: [
    AppComponent
    // Do NOT put ProductsComponent here because it is standalone: true
  ],
  imports: [
    BrowserModule,
    HttpClientModule,           // Add HTTP Client
    RouterModule.forRoot(routes), // Register your routes
    ProductsComponent           // Import Standalone component here
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
