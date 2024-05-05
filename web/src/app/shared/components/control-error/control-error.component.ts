import { Component, computed, DestroyRef, effect, inject, input, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from "@angular/forms";
import { FormErrorHandlerService } from "../../../core/services/form-error-handler.service";
import { takeUntilDestroyed } from "@angular/core/rxjs-interop";
import { distinctUntilChanged } from "rxjs";

const getErrorMessage = (control: FormControl) => {
	const customError = control?.errors?.["customError"];

	if (control?.errors?.["required"])
		return "Campo é obrigatório";
	else if (customError)
		return customError;

	return "";
}

@Component({
	selector: 'app-control-error',
	standalone: true,
	imports: [],
	templateUrl: './control-error.component.html',
	styleUrl: './control-error.component.scss'
})
export class ControlErrorComponent implements OnInit, OnDestroy {
	private errorHandler = inject(FormErrorHandlerService);
	private destroyRef = inject(DestroyRef);

	form = input.required<FormGroup>();
	name = input.required<string>();

	control = computed(() => {
		return this.form().get(this.name()) as FormControl;
	});

	errorMessage?: string;

	updateErrorOnGlobalErrorsChange = effect(() => {
		const globalErrors = this.errorHandler.formErrors();

		const controlErrorMessage = globalErrors[this.name()];

		if (controlErrorMessage)
			this.control().setErrors({
				customError: controlErrorMessage
			});
	});

	ngOnInit(): void {
		this.control().statusChanges
			.pipe(takeUntilDestroyed(this.destroyRef), distinctUntilChanged())
			.subscribe(status => {
				if (status === "VALID")
					this.clearError();
				else if (status === "INVALID")
					this.errorMessage = getErrorMessage(this.control())
			})
	}

	ngOnDestroy() {
		this.clearError();
	}

	private clearError() {
		delete this.errorMessage;
		this.control().setErrors(null);
		this.control().updateValueAndValidity();
		this.errorHandler.clearErrors();
	}
}
