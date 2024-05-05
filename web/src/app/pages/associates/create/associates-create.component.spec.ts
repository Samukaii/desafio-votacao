import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociatesCreateComponent } from './associates-create.component';

describe('VotingSessionsVoteComponent', () => {
  let component: AssociatesCreateComponent;
  let fixture: ComponentFixture<AssociatesCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssociatesCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssociatesCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
