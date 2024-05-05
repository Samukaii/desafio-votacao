import { inject, Injectable, Type } from '@angular/core';
import { DialogService, DynamicDialogConfig } from "primeng/dynamicdialog";

type Generic = Record<string, any>;


export type DialogComponentInfo<T> = T extends {dialogInfo: Generic} ? T["dialogInfo"] : any;


@Injectable({
	providedIn: 'root'
})
export class ApplicationDialogService {
	private dialog = inject(DialogService);

	open<T, D extends DialogComponentInfo<T>>(component: Type<T>, config: DynamicDialogConfig<D>) {
		return this.dialog.open(component, config);
	}

	closeAll() {
		const all = Array.from(this.dialog.dialogComponentRefMap.keys());

		all.forEach(dialog => dialog.close())
	}
}
