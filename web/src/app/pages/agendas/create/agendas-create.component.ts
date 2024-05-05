import { Component, inject } from '@angular/core';
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { FormInputComponent } from "../../../shared/components/form/input/form-input.component";
import { FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { AgendaPayload } from "../models/agenda-payload";
import { FormTextareaComponent } from "../../../shared/components/form/textarea/form-textarea.component";
import { getDialogData } from "../../../shared/functions/get-dialog-data";
import { ApplicationDialogService } from "../../../shared/services/application-dialog.service";
import { requiredField } from "../../../shared/functions/requiredField";

@Component({
  selector: 'app-agendas-create',
  standalone: true,
	imports: [
		ButtonModule,
		InputTextModule,
		FormInputComponent,
		ReactiveFormsModule,
		FormTextareaComponent
	],
  templateUrl: './agendas-create.component.html',
  styleUrl: './agendas-create.component.scss'
})
export class AgendasCreateComponent {
	dialog = inject(ApplicationDialogService);

	dialogInfo = getDialogData<{
		onSubmit: (form: AgendaPayload) => void
	}>();

	form = inject(FormBuilder).nonNullable.group({
		name: [requiredField<string>(), Validators.required],
		description: [requiredField<string>(), Validators.required],
	});

	onSubmit() {
		this.dialogInfo.onSubmit(this.form.getRawValue());
	}
}
