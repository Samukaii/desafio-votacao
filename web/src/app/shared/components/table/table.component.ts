import { Component, computed, input } from '@angular/core';
import { ButtonModule } from "primeng/button";
import { SharedModule } from "primeng/api";
import { TableModule } from "primeng/table";
import { CallPipe } from "../../pipes/call.pipe";
import { TableCellComponent } from "./cell/table-cell.component";
import { ButtonComponent } from "../button/button.component";
import { NoResultsComponent } from "../no-results/no-results.component";
import { NoResults } from "../no-results/models/no-results";
import { Identifiable } from "../../models/identifiable";
import { Button } from "../button/models/button";
import { TableColumn } from "./models/table-column";
import { TableColumnsFn } from "../../models/table-columns-fn";


@Component({
  selector: 'app-table',
  standalone: true,
	imports: [
		ButtonModule,
		SharedModule,
		TableModule,
		CallPipe,
		TableCellComponent,
		ButtonComponent,
		NoResultsComponent
	],
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})
export class TableComponent<T extends Identifiable> {
	tableTitle = input<string>();
	columnsFn = input.required<TableColumnsFn<T>>();
	headerActions = input<Button[]>([]);
	data = input.required<T[]>();

	noResults = input<NoResults>({
		icon: "pi pi-align-center",
		message: "Nenhum resultado encontrado"
	});

	allColumns = computed(() => {
		const columnsFn = this.columnsFn();
		const data = this.data();

		const columns = new Map<number, TableColumn[]>();

		if(!data.length) return columns;

		data.forEach(item => {
			columns.set(item.id, columnsFn(item));
		});

		return columns;
	})

	headers = computed(() => {
		const columnsFn = this.columnsFn();
		const data = this.data();

		if(!data.length) return [];

		return columnsFn(data[0]);
	});

	getColumns(item: T): TableColumn[] {
		return this.allColumns().get(item.id)!;
	}
}
