export interface Agenda {
	id: number;
	name: string;
	description: string;
	status: {
		id: number;
		name: string;
	};
	results: {
		votesInFavor: number;
		votesNotInFavor: number;
		totalVotes: number;
	}
}
