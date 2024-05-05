import { inject, Injectable, signal } from '@angular/core';
import { Associated } from "./models/associated";
import { AssociatesService } from "./associates.service";
import { firstValueFrom } from "rxjs";

@Injectable({
	providedIn: 'root'
})
export class AssociatesRepositoryService {
	private service = inject(AssociatesService);
	data = signal<Associated[]>([]);
	loading = signal(false);

	async fetchAll() {
		this.loading.set(true);
		const results = await this.getAll();
		this.data.set(results);
		this.loading.set(false);
	}

	create() {

	}

	private getAll() {
		return firstValueFrom(this.service.getAll());
	}
}
