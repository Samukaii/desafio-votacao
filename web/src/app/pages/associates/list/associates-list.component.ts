import { Component, inject, OnInit } from '@angular/core';
import { AssociatesRepositoryService } from "../associates-repository.service";
import { TableModule } from "primeng/table";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { DialogModule } from "primeng/dialog";
import { AvatarModule } from "primeng/avatar";
import { TableComponent } from "../../../shared/components/table/table.component";
import { Associated } from "../models/associated";
import { AssociatesCreateComponent } from "../create/associates-create.component";
import { AssociatesActionsService } from "../associates-actions.service";
import { NoResults } from "../../../shared/components/no-results/models/no-results";
import { Button } from "../../../shared/components/button/models/button";
import { TableColumnsFn } from "../../../shared/models/table-columns-fn";
import { PrimeIcons } from "primeng/api";

@Component({
  selector: 'app-associates-list',
  standalone: true,
	imports: [
		TableModule,
		ButtonModule,
		InputTextModule,
		DialogModule,
		AvatarModule,
		TableComponent,
		AssociatesCreateComponent
	],
  templateUrl: './associates-list.component.html',
  styleUrl: './associates-list.component.scss'
})
export class AssociatesListComponent implements OnInit {
	protected repository = inject(AssociatesRepositoryService);
	protected actions = inject(AssociatesActionsService);

	noResults: NoResults = {
		icon: "pi pi-users",
		message: "Nenhum associado cadastrado"
	};

	headerActions: Button[] = [
		{
			icon: "pi pi-plus",
			click: () => this.actions.create()
		}
	];

	columnsFn: TableColumnsFn<Associated> = associated => [
		{
			label: "Nome",
			type: "text",
			value: associated.name,
			key: "name"
		},
		{
			label: "CPF",
			type: "text",
			value: associated.cpf,
			key: "cpf"
		},
		{
			label: "",
			value: "Ações",
			type: "actions",
			key: "actions",
			options: {
				actions: this.getActions(associated)
			}
		}
	]

	private getActions(agenda: Associated): Button[] {
		return [
			{
				label: "Excluir",
				theme: "outlined",
				icon: PrimeIcons.TRASH,
				click: () => this.actions.delete(agenda),
				condition: true,
				severity: "danger",
				size: "small"
			},
		]
	}

	ngOnInit() {
		this.repository.fetchAll();
	}
}
