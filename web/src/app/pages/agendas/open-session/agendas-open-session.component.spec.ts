import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendasOpenSessionComponent } from './agendas-open-session.component';

describe('VotingSessionsVoteComponent', () => {
  let component: AgendasOpenSessionComponent;
  let fixture: ComponentFixture<AgendasOpenSessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgendasOpenSessionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgendasOpenSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
