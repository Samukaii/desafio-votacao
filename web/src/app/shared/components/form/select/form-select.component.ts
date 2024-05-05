import { Component, computed, input } from '@angular/core';
import { InputTextModule } from "primeng/inputtext";
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { InputTextareaModule } from "primeng/inputtextarea";
import { FloatLabelModule } from "primeng/floatlabel";
import { DropdownModule } from "primeng/dropdown";
import { ControlErrorComponent } from "../../control-error/control-error.component";

export interface BaseSelectOption {
	id: number;
	name: string;
}


@Component({
	selector: 'app-form-select',
	standalone: true,
    imports: [
        InputTextModule,
        ReactiveFormsModule,
        InputTextareaModule,
        FloatLabelModule,
        DropdownModule,
        FormsModule,
        ControlErrorComponent
    ],
	templateUrl: './form-select.component.html',
	styleUrl: './form-select.component.scss'
})
export class FormSelectComponent {
	form = input.required<FormGroup>();
	name = input.required<string>();

	label = input<string>();
	placeholder = input<string>();


	control = computed(() => {
		return this.form().get(this.name()) as FormControl;
	})

	options = input<BaseSelectOption[]>([]);
}
