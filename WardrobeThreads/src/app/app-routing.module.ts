import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainPanelComponent } from './_components/main-panel/main-panel.component';
import { ProductsComponent } from './_components/products/products.component';
import { ProductComponent } from './_components/product/product.component';
import { LoginComponent } from './_components/login/login.component';
import { RegisterComponent } from './_components/register/register.component';
import { ProfileInformationsComponent } from './_components/profile-informations/profile-informations.component';
import { PurchaseCompletingComponent } from './_components/purchase-completing/purchase-completing.component';
import { AdminPanelComponent } from './_components/admin-panel/admin-panel.component';
import { PageNotFoundComponent } from './_components/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: 'main', component: MainPanelComponent },
  { path: 'products', component: ProductsComponent },
  { path: 'product/:id', component: ProductComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileInformationsComponent },
  { path: 'purchase', component: PurchaseCompletingComponent },
  { path: 'admin', component: AdminPanelComponent },
  { path: '', redirectTo: '/main', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
