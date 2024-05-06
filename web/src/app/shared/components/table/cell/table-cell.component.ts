import { Component, input } from '@angular/core';
import { NgIf } from "@angular/common";
import { TagModule } from "primeng/tag";
import { ButtonModule } from "primeng/button";
import { ButtonComponent } from "../../button/button.component";
import { TableColumn } from "../models/table-column";

@Component({
  selector: 'app-table-cell',
  standalone: true,
	imports: [
		NgIf,
		TagModule,
		ButtonModule,
		ButtonComponent
	],
  templateUrl: './table-cell.component.html',
  styleUrl: './table-cell.component.scss'
})
export class TableCellComponent {
	column = input.required<TableColumn>();
}
