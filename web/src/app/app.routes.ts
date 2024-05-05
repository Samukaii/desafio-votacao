import { Routes } from '@angular/router';
import { AssociatesListComponent } from "./pages/associates/list/associates-list.component";
import { AgendasListComponent } from "./pages/agendas/list/agendas-list.component";

export const routes: Routes = [
	{
		path: "associates",
		component: AssociatesListComponent
	},
	{
		path: "agendas",
		component: AgendasListComponent
	},
	{
		path: "",
		redirectTo: 'associates',
		pathMatch: 'full'
	},
];
