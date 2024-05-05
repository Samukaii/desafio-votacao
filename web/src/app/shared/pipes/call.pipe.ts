import { ChangeDetectorRef, EmbeddedViewRef, Pipe, PipeTransform } from '@angular/core';

@Pipe({
	name: 'call',
	standalone: true
})
export class CallPipe<C> implements PipeTransform {
	private readonly context: C;

	constructor(private cd: ChangeDetectorRef) {
		this.context = (this.cd as EmbeddedViewRef<C>).context;
	}

	transform<T>(param: unknown, fn: (...args: any[]) => T, ...params: unknown[]): T {
		return fn.apply(this.context, [param, ...params]);
	}
}
