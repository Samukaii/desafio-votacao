import { Component, inject } from '@angular/core';
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { FormInputComponent } from "../../../shared/components/form/input/form-input.component";
import { FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { ApplicationDialogService } from "../../../shared/services/application-dialog.service";
import { getDialogData } from "../../../shared/functions/get-dialog-data";
import { AssociatedPayload } from "../models/associated-payload";
import { requiredField } from "../../../shared/functions/requiredField";

@Component({
  selector: 'app-associates-create',
  standalone: true,
	imports: [
		ButtonModule,
		InputTextModule,
		FormInputComponent,
		ReactiveFormsModule
	],
  templateUrl: './associates-create.component.html',
  styleUrl: './associates-create.component.scss'
})
export class AssociatesCreateComponent {
	dialogInfo = getDialogData<{
		onSubmit: (payload: AssociatedPayload) => void
	}>()

	dialog = inject(ApplicationDialogService);

	form = inject(FormBuilder).nonNullable.group({
		name: [requiredField<string>(), Validators.required],
		cpf: [requiredField<string>(), Validators.required],
	});

	onSubmit() {
		this.dialogInfo.onSubmit(this.form.getRawValue());
	}
}
