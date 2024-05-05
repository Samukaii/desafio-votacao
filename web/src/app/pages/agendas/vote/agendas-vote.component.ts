import { Component, inject } from '@angular/core';
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { FormInputComponent } from "../../../shared/components/form/input/form-input.component";
import { FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { FormTextareaComponent } from "../../../shared/components/form/textarea/form-textarea.component";
import { FormSelectComponent } from "../../../shared/components/form/select/form-select.component";
import { ApplicationDialogService } from "../../../shared/services/application-dialog.service";
import { getDialogData } from "../../../shared/functions/get-dialog-data";
import { FormRadioButtonComponent } from "../../../shared/components/form/radio-button/form-radio-button.component";
import { StepperModule } from "primeng/stepper";
import { DividerModule } from "primeng/divider";
import { Agenda } from "../models/agenda";
import { AgendaVotePayload } from "../../associates/models/agenda-vote-payload";
import { requiredField } from "../../../shared/functions/requiredField";

@Component({
	selector: 'app-agendas-vote',
	standalone: true,
	imports: [
		ButtonModule,
		InputTextModule,
		FormInputComponent,
		ReactiveFormsModule,
		FormTextareaComponent,
		FormSelectComponent,
		FormRadioButtonComponent,
		StepperModule,
		DividerModule
	],
	templateUrl: './agendas-vote.component.html',
	styleUrl: './agendas-vote.component.scss'
})
export class AgendasVoteComponent {
	dialogInfo = getDialogData<{
		agenda: Agenda;
		onSubmit: (form: AgendaVotePayload) => void
	}>();

	dialog = inject(ApplicationDialogService);

	form = inject(FormBuilder).nonNullable.group({
		favorable: [requiredField<boolean>(), Validators.required],
		associatedCpf: [requiredField<string>(), Validators.required],
	});

	options = [
		{
			id: 1,
			name: "Favorável"
		},
		{
			id: 2,
			name: "Não favorável"
		},
	];

	onSubmit() {
		const rawValue = this.form.getRawValue() as any;

		rawValue.favorable = rawValue.favorable === 1
			? true
			: rawValue.favorable === 2
				? false : null;

		this.dialogInfo.onSubmit(rawValue);
	}
}
