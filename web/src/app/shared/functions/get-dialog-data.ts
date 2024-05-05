import { inject } from "@angular/core";
import { DynamicDialogConfig } from "primeng/dynamicdialog";

export const getDialogData = <T>() =>
	inject(DynamicDialogConfig).data as T;

