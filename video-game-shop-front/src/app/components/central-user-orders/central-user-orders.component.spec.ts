import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CentralUserOrdersComponent } from './central-user-orders.component';

describe('CentralUserOrdersComponent', () => {
  let component: CentralUserOrdersComponent;
  let fixture: ComponentFixture<CentralUserOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CentralUserOrdersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CentralUserOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
