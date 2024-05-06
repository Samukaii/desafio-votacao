import { ColorSeverity } from "../../../models/color-severity";

export interface Button {
	icon?: string;
	label?: string;
	condition?: boolean;
	size?: 'small' | 'large';
	severity?: ColorSeverity;
	theme?: 'raised' | 'outlined' | 'plain';
	click: () => void;
}
