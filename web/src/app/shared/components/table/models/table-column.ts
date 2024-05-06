import { ColorSeverity } from "../../../models/color-severity";
import { Button } from "../../button/models/button";

interface TableOptions {
	default: {
		type: "text"
	},
	badge: {
		type: "badge",
		options: {
			severity: ColorSeverity;
		}
	},
	actions: {
		type: "actions",
		options: {
			actions: Button[];
		}
	},
}

interface CommonColumn {
	key: string;
	label: string;
	value: string;
}

export type TableColumn = CommonColumn & TableOptions[keyof TableOptions];
