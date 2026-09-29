import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { ClientService } from '../../../services/client.service';
import { Client } from '../../../models/client.model';

@Component({
  selector: 'app-client-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.scss']
})
export class ClientFormComponent implements OnInit {
  client: Client = {
    clientsReference: 0,
    clientsRaisonSocial: '',
    clientsAdresse: '',
    clientsCodePostal: '',
    clientsTelephone: '',
    clientsCodeTVA: '',
    clientsFax: '',
    clientsGouvernorat: '',
    clientsPays: ''
  };
  isEdit = false;
  id: number = 0;

  constructor(
    private clientService: ClientService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    if (this.id) {
      this.isEdit = true;
      this.clientService.getClientById(this.id).subscribe({
        next: (data) => this.client = data
      });
    }
  }

  save(): void {
    if (this.isEdit) {
      this.clientService.updateClient(this.id, this.client).subscribe({
        next: () => this.router.navigate(['/clients'])
      });
    } else {
      this.clientService.createClient(this.client).subscribe({
        next: () => this.router.navigate(['/clients'])
      });
    }
  }
}