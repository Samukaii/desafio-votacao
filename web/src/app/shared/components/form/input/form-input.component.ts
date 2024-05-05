import { Component, computed, input } from '@angular/core';
import { InputTextModule } from "primeng/inputtext";
import { FormControl, FormGroup, ReactiveFormsModule } from "@angular/forms";
import { ControlErrorComponent } from "../../control-error/control-error.component";

@Component({
	selector: 'app-form-input',
	standalone: true,
    imports: [
        InputTextModule,
        ReactiveFormsModule,
        ControlErrorComponent
    ],
	templateUrl: './form-input.component.html',
	styleUrl: './form-input.component.scss'
})
export class FormInputComponent {
	form = input.required<FormGroup>();
	name = input.required<string>();

	type = input<string>();
	label = input<string>();
	placeholder = input<string>();


	control = computed(() => {
		return this.form().get(this.name()) as FormControl;
	})
}
