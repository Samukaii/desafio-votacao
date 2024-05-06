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
	private messagesService = inject(MessageService);
	private repository = inject(AssociatesRepositoryService);
	private dialogService = inject(ApplicationDialogService);
	private confirmService = inject(ConfirmationService);

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

						this.messagesService.add({
							summary: "Usuário adicionado com sucesso",
							severity: "success"
						});
					});
				}
			}
		});
	}

	delete(associated: Associated) {
		this.confirmService.confirm({
			message: 'Você tem certeza de que deseja excluir este associado?',
			header: 'Excluir associado',
			icon: 'pi pi-exclamation-triangle',
			acceptIcon:"none",
			rejectIcon:"none",
			rejectButtonStyleClass:"p-button-text",
			accept: () => {
				this.service.delete(associated.id).subscribe(
					() => {
						this.repository.fetchAll();

						this.messagesService.add({
							summary: "Usuário excluído com sucesso",
							severity: "success"
						});
					}
				);
			},
		});
	}
}
