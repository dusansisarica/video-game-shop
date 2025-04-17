import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableShopsComponent } from './available-shops.component';

describe('AvailableShopsComponent', () => {
  let component: AvailableShopsComponent;
  let fixture: ComponentFixture<AvailableShopsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailableShopsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvailableShopsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
