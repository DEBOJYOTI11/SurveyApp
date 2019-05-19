import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PollComponentComponent } from './poll-component.component';

describe('PollComponentComponent', () => {
  let component: PollComponentComponent;
  let fixture: ComponentFixture<PollComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PollComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PollComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
