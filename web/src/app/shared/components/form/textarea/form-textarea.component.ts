import { Component, computed, input } from '@angular/core';
import { InputTextModule } from "primeng/inputtext";
import { FormControl, FormGroup, ReactiveFormsModule } from "@angular/forms";
import { InputTextareaModule } from "primeng/inputtextarea";
import { ControlErrorComponent } from "../../control-error/control-error.component";


@Component({
	selector: 'app-form-textarea',
	standalone: true,
	imports: [
		InputTextModule,
		ReactiveFormsModule,
		InputTextareaModule,
		ControlErrorComponent
	],
	templateUrl: './form-textarea.component.html',
	styleUrl: './form-textarea.component.scss'
})
export class FormTextareaComponent {
	form = input.required<FormGroup>();
	name = input.required<string>();
	maxLength = input(250);

	label = input<string>();
	placeholder = input<string>();

	control = computed(() => {
		return this.form().get(this.name()) as FormControl;
	});
}
