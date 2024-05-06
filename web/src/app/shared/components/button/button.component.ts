import { Component, input } from '@angular/core';
import { ButtonModule } from "primeng/button";
import { Button } from "./models/button";

@Component({
	selector: 'app-button',
	standalone: true,
	imports: [
		ButtonModule
	],
	templateUrl: './button.component.html',
	styleUrl: './button.component.scss'
})
export class ButtonComponent {
	action = input.required<Button>()
}
