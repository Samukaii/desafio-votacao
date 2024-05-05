import { inject, Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../environments/environment";
import { Agenda } from "./models/agenda";
import { AgendaPayload } from "./models/agenda-payload";
import { AgendaOpenSessionPayload } from "../associates/models/agenda-open-session-payload";
import { AgendaVotePayload } from "../associates/models/agenda-vote-payload";

@Injectable({
	providedIn: 'root'
})
export class AgendasService {
	private http = inject(HttpClient);

	getAll() {
		return this.http.get<Agenda[]>(`${environment.api}/agendas`);
	}

	create(params: AgendaPayload) {
		return this.http.post<Agenda>(`${environment.api}/agendas`, params);
	}

	openSession(id: number, params: AgendaOpenSessionPayload) {
		return this.http.post<Agenda>(`${environment.api}/agendas/${id}/open_voting_session`, params);
	}

	vote(id: number, params: AgendaVotePayload) {
		return this.http.post<Agenda>(`${environment.api}/agendas/${id}/vote`, params);
	}
}
