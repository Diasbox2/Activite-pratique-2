import { Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { CustomersComponent } from './customers/customers.component';

export const routes: Routes = [
  { path: '', redirectTo: 'products', pathMatch: 'full' },
  { path: 'products', component: ProductsComponent },
  { path: 'customers', component: CustomersComponent }
];
