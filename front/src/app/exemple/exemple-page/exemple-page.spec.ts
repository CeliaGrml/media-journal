import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ExemplePage } from './exemple-page';

describe('ExemplePage', () => {
  let component: ExemplePage;
  let fixture: ComponentFixture<ExemplePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExemplePage],
    }).compileComponents();

    fixture = TestBed.createComponent(ExemplePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
