import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendasListComponent } from './agendas-list.component';

describe('VotingSessionsListComponent', () => {
  let component: AgendasListComponent;
  let fixture: ComponentFixture<AgendasListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgendasListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgendasListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
