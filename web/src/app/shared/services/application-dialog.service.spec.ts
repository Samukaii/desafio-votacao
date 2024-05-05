import { TestBed } from '@angular/core/testing';

import { ApplicationDialogService } from './application-dialog.service';

describe('ApplicationDialogService', () => {
  let service: ApplicationDialogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApplicationDialogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
