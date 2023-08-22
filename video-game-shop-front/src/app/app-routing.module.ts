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

const routes: Routes = [
  { path: '', component: FeaturedGamesComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'home', component: HomeComponent },
  { path: 'profile', component: UserProfileComponent, children: [
    { path: 'profile', redirectTo: 'details', pathMatch: 'full' },
    { path: 'details', component: UserDetailsComponent },
    { path: 'wish-list', component: WishListComponent },
    { path: 'previous-orders', component: PreviousOrdersComponent },
    { path: 'comments', component: UserCommentsComponent }
  ]},
  { path: 'games/:id', component: GameDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
