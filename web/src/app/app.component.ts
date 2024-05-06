import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenuModule } from "primeng/menu";
import { MenuComponent } from "./core/menu/menu.component";
import { DialogModule } from "primeng/dialog";
import { ToastModule } from "primeng/toast";
import { ConfirmDialogModule } from "primeng/confirmdialog";

@Component({
	selector: 'app-root',
	standalone: true,
	imports: [RouterOutlet, MenuModule, MenuComponent, DialogModule, ToastModule, ConfirmDialogModule],
	templateUrl: './app.component.html',
	styleUrl: './app.component.scss'
})
export class AppComponent {

}
