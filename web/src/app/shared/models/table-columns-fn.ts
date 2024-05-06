import { TableColumn } from "../components/table/models/table-column";

export type TableColumnsFn<T> = (column: T) => TableColumn[];
