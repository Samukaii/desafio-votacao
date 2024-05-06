import { Component, input } from '@angular/core';

@Component({
  selector: 'app-no-results',
  standalone: true,
  imports: [],
  templateUrl: './no-results.component.html',
  styleUrl: './no-results.component.scss'
})
export class NoResultsComponent {
	icon = input<string>("pi pi-align-center");
	message = input<string>("Nenhum resultado encontrado");
}
