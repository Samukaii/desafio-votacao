import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenuModule } from "primeng/menu";
import { MenuComponent } from "./core/menu/menu.component";
import { DialogModule } from "primeng/dialog";
import { ToastModule } from "primeng/toast";

@Component({
	selector: 'app-root',
	standalone: true,
	imports: [RouterOutlet, MenuModule, MenuComponent, DialogModule, ToastModule],
	templateUrl: './app.component.html',
	styleUrl: './app.component.scss'
})
export class AppComponent {

}
