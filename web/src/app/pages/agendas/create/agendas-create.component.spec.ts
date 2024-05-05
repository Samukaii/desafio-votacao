import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendasCreateComponent } from './agendas-create.component';

describe('VotingSessionsVoteComponent', () => {
  let component: AgendasCreateComponent;
  let fixture: ComponentFixture<AgendasCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgendasCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgendasCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
