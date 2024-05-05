import { Component, inject, OnInit, signal } from '@angular/core';
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { FormInputComponent } from "../../../shared/components/form/input/form-input.component";
import { FormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { FormTextareaComponent } from "../../../shared/components/form/textarea/form-textarea.component";
import { FormSelectComponent } from "../../../shared/components/form/select/form-select.component";
import { AgendasService } from "../agendas.service";
import { Agenda } from "../models/agenda";
import { firstValueFrom } from "rxjs";
import { getDialogData } from "../../../shared/functions/get-dialog-data";
import { ApplicationDialogService } from "../../../shared/services/application-dialog.service";
import { AgendaOpenSessionPayload } from "../../associates/models/agenda-open-session-payload";
import { field } from "../../../shared/functions/field";

@Component({
  selector: 'app-agendas-open-session',
  standalone: true,
	imports: [
		ButtonModule,
		InputTextModule,
		FormInputComponent,
		ReactiveFormsModule,
		FormTextareaComponent,
		FormSelectComponent
	],
  templateUrl: './agendas-open-session.component.html',
  styleUrl: './agendas-open-session.component.scss'
})
export class AgendasOpenSessionComponent implements OnInit {
	dialogInfo = getDialogData<{
		onSubmit: (form: AgendaOpenSessionPayload) => void
	}>();

	form = inject(FormBuilder).nonNullable.group({
		timeInMinutes: [field<number>(), Validators.required],
	});

	dialog = inject(ApplicationDialogService);

	options = signal<Agenda[]>([])
	agendasService = inject(AgendasService);

	async ngOnInit() {
		const options = await firstValueFrom(this.agendasService.getAll());
		this.options.set(options);
	}

	onSubmit() {
		this.dialogInfo.onSubmit(this.form.getRawValue());
	}
}
