import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FeaturedGamesComponent } from './components/featured-games/featured-games.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { HomeComponent } from './components/home/home.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { WishListComponent } from './components/wish-list/wish-list.component';
import { PreviousOrdersComponent } from './components/previous-orders/previous-orders.component';
import { UserCommentsComponent } from './components/user-comments/user-comments.component';
import { GameDetailsComponent } from './components/game-details/game-details.component';
import { ShopComponent } from './components/shop/shop.component';
import { CartComponent } from './components/cart/cart.component';
import { GameSettingsComponent } from './components/game-settings/game-settings.component';
import { ShopSettingsComponent } from './components/shop-settings/shop-settings.component';
import { ReviewSettingsComponent } from './components/review-settings/review-settings.component';
import { UserSettingsComponent } from './components/user-settings/user-settings.component';
import { InventorySettingsComponent } from './components/inventory-settings/inventory-settings.component';
import { OrderSettingsComponent } from './components/order-settings/order-settings.component';
import { CentralUserOrdersComponent } from './components/central-user-orders/central-user-orders.component';
import { CentralInventoryComponent } from './components/central-inventory/central-inventory.component';
import { ShopItemsComponent } from './components/shop-items/shop-items.component';
import { ShopStorageManagerComponent } from './components/shop-storage-manager/shop-storage-manager.component';
import { HeatmapComponent } from './components/heatmap/heatmap.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'home', component: HomeComponent },
  { path: 'shop', component: ShopItemsComponent },
  {
    path: 'profile', component: UserProfileComponent, children: [
      { path: 'profile', redirectTo: 'details', pathMatch: 'full' },
      { path: 'details', component: UserDetailsComponent },
      { path: 'wish-list', component: WishListComponent },
      { path: 'previous-orders', component: PreviousOrdersComponent },
      { path: 'comments', component: UserCommentsComponent },
      { path: 'game-settings', component: GameSettingsComponent },
      { path: 'shop-settings', component: ShopSettingsComponent },
      { path: 'review-settings', component: ReviewSettingsComponent },
      { path: 'user-settings', component: UserSettingsComponent },
      { path: 'inventory-settings', component: InventorySettingsComponent },
      { path: 'order-settings', component: OrderSettingsComponent },
      { path: 'central-user-orders', component: CentralUserOrdersComponent },
      { path: 'central-inventory', component: CentralInventoryComponent },
      { path: 'shop-inventory', component: ShopStorageManagerComponent },
    ]
  },
  { path: 'games/:id', component: GameDetailsComponent },
  { path: 'shop/:id', component: ShopComponent },
  { path: 'cart', component: CartComponent },
  { path: 'map', component: HeatmapComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
