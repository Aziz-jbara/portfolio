import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

interface SidebarMenuItem {
  label: string;
  icon: string;
  route: string;
}

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {

  isCollapsed = false;

  menuItems: SidebarMenuItem[] = [
    {
      label: 'Dashboard',
      icon: 'bi-speedometer2',
      route: '/dashboard'
    },
    {
      label: 'Clients',
      icon: 'bi-people',
      route: '/clients'
    },
    {
      label: 'Fournisseurs',
      icon: 'bi-truck',
      route: '/fournisseurs'
    },
    {
      label: 'Factures Client',
      icon: 'bi-file-earmark-text',
      route: '/factures-client'
    },
    {
      label: 'Factures Fournisseur',
      icon: 'bi-file-earmark-invoice',
      route: '/factures-frs'
    },
    {
      label: 'Transactions Banque',
      icon: 'bi-bank',
      route: '/transactions-banque'
    },
    {
      label: 'Transactions Caisse',
      icon: 'bi-cash-stack',
      route: '/transactions-caisse'
    }
  ];

  toggleSidebar(): void {
    this.isCollapsed = !this.isCollapsed;
  }

  trackByRoute(index: number, item: SidebarMenuItem): string {
    return item.route;
  }
}