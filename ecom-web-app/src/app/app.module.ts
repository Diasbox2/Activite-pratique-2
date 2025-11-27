import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { routes } from './app.routes'; // Ensure this path is correct for your project
import { ProductsComponent } from './products/products.component';
import { CustomersComponent } from './customers/customers.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent // 1. CLASSIC component goes here
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    ProductsComponent  // 2. STANDALONE component goes here
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
