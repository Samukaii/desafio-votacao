import { inject, Injectable, signal } from '@angular/core';
import { Agenda } from "./models/agenda";
import { AgendasService } from "./agendas.service";
import { firstValueFrom } from "rxjs";

@Injectable({
	providedIn: 'root'
})
export class AgendasRepositoryService {
	private service = inject(AgendasService);
	data = signal<Agenda[]>([]);
	loading = signal(false);

	async fetchAll() {
		this.loading.set(true);
		const results = await this.getAll();
		this.data.set(results);
		this.loading.set(false);
	}

	private getAll() {
		return firstValueFrom(this.service.getAll());
	}
}
