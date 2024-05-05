import { inject, Injectable } from '@angular/core';
import { Agenda } from "./models/agenda";
import { AgendasRepositoryService } from "./agendas-repository.service";
import { AgendasService } from "./agendas.service";
import { ApplicationDialogService } from "../../shared/services/application-dialog.service";
import { AgendasOpenSessionComponent } from "./open-session/agendas-open-session.component";
import { AgendasVoteComponent } from "./vote/agendas-vote.component";
import { AgendasCreateComponent } from "./create/agendas-create.component";

@Injectable({
	providedIn: 'root'
})
export class AgendasActionsService {
	private service = inject(AgendasService);
	private repository = inject(AgendasRepositoryService);
	private dialogService = inject(ApplicationDialogService);

	create() {
		this.dialogService.open(AgendasCreateComponent, {
			dismissableMask: true,
			header: "Adicionar pauta",
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

	openSession(agenda: Agenda) {
		this.dialogService.open(AgendasOpenSessionComponent, {
			dismissableMask: true,
			header: "Abrir votações",
			width: "700px",
			data: {
				onSubmit: form => {
					this.service.openSession(agenda.id, form).subscribe(() => {
						this.repository.fetchAll();
						this.dialogService.closeAll();
					});
				}
			}
		});
	}

	vote(agenda: Agenda) {
		this.dialogService.open(AgendasVoteComponent, {
			dismissableMask: true,
			header: agenda.name,
			width: "700px",
			data: {
				agenda,
				onSubmit: form => {
					this.service.vote(agenda.id, form).subscribe(() => {
						this.repository.fetchAll();
						this.dialogService.closeAll();
					});
				}
			}
		});
	}
}
