import { Injectable, signal } from '@angular/core';
import { FieldErrors } from "../../shared/models/api-error";

@Injectable({
  providedIn: 'root'
})
export class FormErrorHandlerService {
	formErrors = signal<Record<string, string>>({});

	handleGlobalErrors(error: FieldErrors) {
		error.fieldErrors.forEach(error => {
			this.formErrors.update(errors => {
				return {
					...errors,
					[error.fieldName]: error.message
				};
			})
		})
	}

	clearErrors() {
		this.formErrors.set({});
	}
}
