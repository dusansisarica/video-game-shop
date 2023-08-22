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
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { provideAnimations } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/home/home.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { WishListComponent } from './components/wish-list/wish-list.component';
import { PreviousOrdersComponent } from './components/previous-orders/previous-orders.component';
import { UserCommentsComponent } from './components/user-comments/user-comments.component';
import { AuthInterceptor } from './interceptors/auth-interceptor';
import { GameDetailsComponent } from './components/game-details/game-details.component';




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
    FormsModule
  ],
  providers: [
  provideAnimations(),
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
