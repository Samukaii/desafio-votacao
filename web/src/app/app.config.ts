import { ApplicationConfig } from '@angular/core';
import { provideRouter, withViewTransitions } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from "@angular/common/http";
import { provideAnimations } from "@angular/platform-browser/animations";
import { DialogService } from "primeng/dynamicdialog";
import { ConfirmationService, MessageService } from "primeng/api";
import { errorsInterceptor } from "./core/interceptors/errors.interceptor";

export const appConfig: ApplicationConfig = {
	providers: [
		provideRouter(routes, withViewTransitions()),
		provideClientHydration(),
		provideHttpClient(
			withFetch(),
			withInterceptors([
				errorsInterceptor
			])
		),
		provideAnimations(),
		DialogService,
		MessageService,
		ConfirmationService
	]
};
