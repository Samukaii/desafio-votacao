import { Component } from '@angular/core';
import { MenuModule } from "primeng/menu";
import { MenuItem, PrimeIcons } from "primeng/api";

@Component({
  selector: 'app-menu',
  standalone: true,
    imports: [
        MenuModule
    ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {
	items: MenuItem[] = [
		{
			label: "Associados",
			icon: PrimeIcons.USERS,
			iconStyle: {
				fontSize: '1.4rem',
			},
			routerLink: "/associates",
		},
		{
			label: "Pautas",
			icon: PrimeIcons.BOOK,
			iconStyle: {
				fontSize: '1.4rem',
			},
			routerLink: "/agendas"
		},
	]
}
