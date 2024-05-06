import { Component, inject, OnInit } from '@angular/core';
import { AgendasRepositoryService } from "../agendas-repository.service";
import { TableModule } from "primeng/table";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { DialogModule } from "primeng/dialog";
import { AvatarModule } from "primeng/avatar";
import { TableComponent } from "../../../shared/components/table/table.component";
import { Agenda } from "../models/agenda";
import { AgendasCreateComponent } from "../create/agendas-create.component";
import { AgendasActionsService } from "../agendas-actions.service";
import { AgendaStatusEnum } from "../enums/agenda-status-enum";
import { ColorSeverity } from "../../../shared/models/color-severity";
import { PrimeIcons } from "primeng/api";
import { NoResults } from "../../../shared/components/no-results/models/no-results";
import { Button } from "../../../shared/components/button/models/button";
import { TableColumnsFn } from "../../../shared/models/table-columns-fn";

@Component({
	selector: 'app-agendas-list',
	standalone: true,
	imports: [
		TableModule,
		ButtonModule,
		InputTextModule,
		DialogModule,
		AvatarModule,
		TableComponent,
		AgendasCreateComponent
	],
	templateUrl: './agendas-list.component.html',
	styleUrl: './agendas-list.component.scss'
})
export class AgendasListComponent implements OnInit {
	protected repository = inject(AgendasRepositoryService);
	protected actions = inject(AgendasActionsService);

	noResults: NoResults = {
		icon: "pi pi-book",
		message: "Nenhuma pauta cadastrada"
	};

	headerActions: Button[] = [
		{
			icon: "pi pi-plus",
			click: () => this.actions.create()
		}
	];

	columnsFn: TableColumnsFn<Agenda> = agenda => [
		{
			label: "Nome",
			type: "text",
			value: agenda.name,
			key: "name"
		},
		{
			label: "Descrição",
			type: "text",
			value: agenda.description,
			key: "description"
		},
		{
			label: "Situação",
			type: "badge",
			value: agenda.status.name,
			options: {
				severity: this.getStatusColor(agenda)
			},
			key: "status"
		},
		{
			label: "A favor / Contra / Total",
			type: "text",
			value: this.getResult(agenda),
			options: {
				severity: this.getStatusColor(agenda)
			},
			key: "totalVotes"
		},
		{
			label: "",
			type: "actions",
			value: agenda.status.name,
			key: "actions",
			options: {
				actions: this.getActions(agenda)
			},
		},
	]


	private getActions(agenda: Agenda): Button[] {
		return [
			{
				label: "Votar",
				theme: "raised",
				icon: PrimeIcons.PENCIL,
				click: () => this.actions.vote(agenda),
				condition: this.canVote(agenda),
				severity: "primary",
				size: "small"
			},
			{
				label: "Abrir votações",
				icon: PrimeIcons.ARROW_UP_RIGHT,
				theme: "outlined",
				click: () => this.actions.openSession(agenda),
				condition: this.canOpenVotingSession(agenda),
				severity: "primary",
				size: "small"
			},
		]
	}

	ngOnInit() {
		this.repository.fetchAll();
	}

	private canOpenVotingSession(agenda: Agenda) {
		return agenda.status.id === AgendaStatusEnum.NOT_STARTED;
	}

	private canVote(agenda: Agenda) {
		return agenda.status.id === AgendaStatusEnum.OPENED;
	}

	private getResult(agenda: Agenda) {
		const {votesInFavor, votesNotInFavor, totalVotes} = agenda.results;

		return `${votesInFavor} / ${votesNotInFavor} / ${totalVotes}`;
	}

	private getStatusColor(agenda: Agenda): ColorSeverity {
		switch (agenda.status.id) {
			case AgendaStatusEnum.NOT_STARTED:
				return "warning";
			case AgendaStatusEnum.OPENED:
				return "info";
			case AgendaStatusEnum.REJECTED:
				return "danger";
			case AgendaStatusEnum.ACCEPTED:
				return "success";
			default:
				return "secondary";
		}
	}
}
