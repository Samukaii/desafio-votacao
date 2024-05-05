import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from "@angular/core";
import { MessageService } from "primeng/api";
import { catchError, throwError } from "rxjs";
import { ApiError } from "../../shared/models/api-error";
import { FormErrorHandlerService } from "../services/form-error-handler.service";


export const errorsInterceptor: HttpInterceptorFn = (req, next) => {
	const messagesService = inject(MessageService);
	const formErrorHandlerService = inject(FormErrorHandlerService);

	return next(req).pipe(
		catchError((response) => {
			const apiError = response.error as ApiError;

			if ('baseError' in apiError)
				messagesService.add({
					summary: apiError.baseError, severity: "error",
				});
			else if ('fieldErrors' in apiError)
				formErrorHandlerService.handleGlobalErrors(apiError)

			return throwError(() => new Error(response.message));
		})
	)
};
