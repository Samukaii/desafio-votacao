import { FormControl } from "@angular/forms";

export const getErrorMessage = (control: FormControl) => {
	const customError = control?.errors?.["customError"];

	if (control?.errors?.["required"])
		return "Campo é obrigatório";
	else if (customError)
		return customError;

	return "";
}
