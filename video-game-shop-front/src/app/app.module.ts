import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FeaturedGamesComponent } from './components/featured-games/featured-games.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { FooterComponent } from './components/footer/footer.component';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { provideAnimations, BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/home/home.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { WishListComponent } from './components/wish-list/wish-list.component';
import { PreviousOrdersComponent } from './components/previous-orders/previous-orders.component';
import { UserCommentsComponent } from './components/user-comments/user-comments.component';
import { AuthInterceptor } from './interceptors/auth-interceptor';
import { GameDetailsComponent } from './components/game-details/game-details.component';
import { ShopComponent } from './components/shop/shop.component';
import { GameCardsComponent } from './components/game-cards/game-cards.component';
import { CartComponent } from './components/cart/cart.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table'
import { MatSortModule } from '@angular/material/sort';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { DatePipe, registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { DemoNgZorroAntdModule } from './ng-zorro-antd.module';
import { ReviewsComponent } from './components/reviews/reviews.component';
import { GameSettingsComponent } from './components/game-settings/game-settings.component';
import { ShopSettingsComponent } from './components/shop-settings/shop-settings.component';
import { ReviewSettingsComponent } from './components/review-settings/review-settings.component';
import { AddGameModalComponent } from './components/add-game-modal/add-game-modal.component';
import { CommonModule } from '@angular/common';
import { UpdateGameModalComponent } from './components/update-game-modal/update-game-modal.component';
import { ShopCardsComponent } from './components/shop-cards/shop-cards.component';
import { GameShopAddComponent } from './components/game-shop-add/game-shop-add.component';
import { AddShopModalComponent } from './components/add-shop-modal/add-shop-modal.component';
import { UpdateShopModalComponent } from './components/update-shop-modal/update-shop-modal.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { UserSettingsComponent } from './components/user-settings/user-settings.component';
import { UserCardsComponent } from './components/user-cards/user-cards.component';
import { UserEmployModalComponent } from './components/user-employ-modal/user-employ-modal.component';
import { InventoryCardsComponent } from './components/inventory-cards/inventory-cards.component';
import { InventorySettingsComponent } from './components/inventory-settings/inventory-settings.component';
import { OrderCardsComponent } from './components/order-cards/order-cards.component';
import { OrderSettingsComponent } from './components/order-settings/order-settings.component';
import { CentralUserOrdersComponent } from './components/central-user-orders/central-user-orders.component';
import { CentralInventoryComponent } from './components/central-inventory/central-inventory.component';
import { OrderDetailsModalComponent } from './components/order-details-modal/order-details-modal.component';
import { ShopItemsComponent } from './components/shop-items/shop-items.component';
import { ShopStorageManagerComponent } from './components/shop-storage-manager/shop-storage-manager.component';
import { AvailableShopsComponent } from './components/available-shops/available-shops.component';
import { ShopMapComponent } from './components/shop-map/shop-map.component';
import { AddManagerComponent } from './components/add-manager/add-manager.component';
import { GamePriceUpdateComponent } from './components/game-price-update/game-price-update.component';
import { MapboxService } from './services/mapbox.service';

registerLocaleData(en);






@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FeaturedGamesComponent,
    AboutUsComponent,
    FooterComponent,
    LoginComponent,
    RegistrationComponent,
    HomeComponent,
    UserProfileComponent,
    UserDetailsComponent,
    WishListComponent,
    PreviousOrdersComponent,
    UserCommentsComponent,
    GameDetailsComponent,
    ShopComponent,
    GameCardsComponent,
    CartComponent,
    ReviewsComponent,
    GameSettingsComponent,
    ReviewSettingsComponent,
    ShopSettingsComponent,
    AddGameModalComponent,
    UpdateGameModalComponent,
    ShopCardsComponent,
    GameShopAddComponent,
    AddShopModalComponent,
    UpdateShopModalComponent,
    PaginationComponent,
    UserSettingsComponent,
    UserCardsComponent,
    UserEmployModalComponent,
    InventoryCardsComponent,
    InventorySettingsComponent,
    OrderCardsComponent,
    OrderSettingsComponent,
    CentralUserOrdersComponent,
    CentralInventoryComponent,
    OrderDetailsModalComponent,
    ShopItemsComponent,
    ShopStorageManagerComponent,
    AvailableShopsComponent,
    ShopMapComponent,
    AddManagerComponent,
    GamePriceUpdateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatCardModule,
    MatToolbarModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatListModule,
    MatTableModule,
    MatSortModule,
    BrowserAnimationsModule,
    DemoNgZorroAntdModule,
    CommonModule,
  ],
  providers: [
    DatePipe,
    MapboxService,
    provideAnimations(),
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: NZ_I18N, useValue: en_US }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
