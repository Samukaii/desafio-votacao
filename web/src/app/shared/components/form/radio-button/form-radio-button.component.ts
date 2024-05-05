import { Component, computed, input } from '@angular/core';
import { InputTextModule } from "primeng/inputtext";
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { InputTextareaModule } from "primeng/inputtextarea";
import { FloatLabelModule } from "primeng/floatlabel";
import { DropdownModule } from "primeng/dropdown";
import { BaseSelectOption } from "../select/form-select.component";
import { RadioButtonModule } from "primeng/radiobutton";
import { ControlErrorComponent } from "../../control-error/control-error.component";

@Component({
	selector: 'app-form-radio-button',
	standalone: true,
    imports: [
        InputTextModule,
        ReactiveFormsModule,
        InputTextareaModule,
        FloatLabelModule,
        DropdownModule,
        FormsModule,
        RadioButtonModule,
        ControlErrorComponent
    ],
	templateUrl: './form-radio-button.component.html',
	styleUrl: './form-radio-button.component.scss'
})
export class FormRadioButtonComponent {
	form = input.required<FormGroup>();
	name = input.required<string>();

	label = input<string>();

	control = computed(() => {
		return this.form().get(this.name()) as FormControl;
	})

	options = input<BaseSelectOption[]>([]);
}
