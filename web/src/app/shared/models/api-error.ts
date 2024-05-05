export interface BaseError {
	baseError: string;
}

export interface FieldErrors {
	fieldErrors: {
		fieldName: string;
		message: string;
	}[];
}

export type ApiError = BaseError | FieldErrors;
