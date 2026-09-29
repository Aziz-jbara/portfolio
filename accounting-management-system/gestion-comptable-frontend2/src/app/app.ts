import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  RouterOutlet,
  Router,
  NavigationStart,
  NavigationEnd,
  NavigationCancel,
  NavigationError
} from '@angular/router';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';

import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { SidebarComponent } from './components/shared/sidebar/sidebar.component';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    CommonModule,
    NavbarComponent,
    SidebarComponent
  ],
  templateUrl: './app.html',
  styleUrls: ['./app.scss']
})
export class App implements OnInit, OnDestroy {

  isLoggedIn = false;

  private subscriptions = new Subscription();

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();

    this.subscriptions.add(
      this.authService.loggedIn$.subscribe({
        next: (loggedIn) => {
          console.log('Auth state changed:', loggedIn);
          this.isLoggedIn = loggedIn;
        }
      })
    );

    this.subscriptions.add(
      this.router.events.subscribe({
        next: (event) => {
          if (event instanceof NavigationStart) {
            console.log('Router NavigationStart:', event.url);
          }

          if (event instanceof NavigationEnd) {
            console.log('Router NavigationEnd:', event.urlAfterRedirects);
            this.isLoggedIn = this.authService.isLoggedIn();
          }

          if (event instanceof NavigationCancel) {
            console.warn('Router NavigationCancel:', event.reason);
          }

          if (event instanceof NavigationError) {
            console.error('Router NavigationError:', event.error);
          }
        }
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}