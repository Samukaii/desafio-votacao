import { TestBed } from '@angular/core/testing';

import { AssociatesService } from './associates.service';

describe('VotingSessionsService', () => {
  let service: AssociatesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssociatesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
