import { Component, inject, OnInit } from '@angular/core';
import { AssociatesRepositoryService } from "../associates-repository.service";
import { TableModule } from "primeng/table";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { DialogModule } from "primeng/dialog";
import { AvatarModule } from "primeng/avatar";
import { Button, TableColumnsFn, TableComponent } from "../../../shared/components/table/table.component";
import { Associated } from "../models/associated";
import { AssociatesCreateComponent } from "../create/associates-create.component";
import { AssociatesActionsService } from "../associates-actions.service";

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
	]

	ngOnInit() {
		this.repository.fetchAll();
	}
}