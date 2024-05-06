import { inject, Injectable } from '@angular/core';
import { AssociatesRepositoryService } from "./associates-repository.service";
import { AssociatesService } from "./associates.service";
import { ApplicationDialogService } from "../../shared/services/application-dialog.service";
import { AssociatesCreateComponent } from "./create/associates-create.component";
import { ConfirmationService, MessageService } from "primeng/api";
import { Associated } from "./models/associated";

@Injectable({
	providedIn: 'root'
})
export class AssociatesActionsService {
	private service = inject(AssociatesService);
	private repository = inject(AssociatesRepositoryService);
	private dialogService = inject(ApplicationDialogService);

	create() {
		this.dialogService.open(AssociatesCreateComponent, {
			dismissableMask: true,
			header: "Adicionar associado",
			styleClass: "without-title-padding",
			width: "700px",
			data: {
				onSubmit: form => {
					this.service.create(form).subscribe(() => {
						this.repository.fetchAll();
						this.dialogService.closeAll();
					});
				}
			}
		});
	}
}
