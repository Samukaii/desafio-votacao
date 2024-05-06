import { inject, Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../environments/environment";
import { Associated } from "./models/associated";
import { AssociatedPayload } from "./models/associated-payload";

@Injectable({
	providedIn: 'root'
})
export class AssociatesService {
	private http = inject(HttpClient);

	getAll() {
		return this.http.get<Associated[]>(`${environment.api}/associates`);
	}

	create(params: AssociatedPayload) {
		return this.http.post<Associated>(`${environment.api}/associates`, params);
	}

	delete(id: number) {
		return this.http.delete(`${environment.api}/associates/${id}`);
	}
}
