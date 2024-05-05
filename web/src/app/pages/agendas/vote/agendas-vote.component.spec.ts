import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendasVoteComponent } from './agendas-vote.component';

describe('VotingSessionsVoteComponent', () => {
  let component: AgendasVoteComponent;
  let fixture: ComponentFixture<AgendasVoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgendasVoteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgendasVoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
