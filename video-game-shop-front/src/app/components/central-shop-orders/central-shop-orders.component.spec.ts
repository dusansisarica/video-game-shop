import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CentralShopOrdersComponent } from './central-shop-orders.component';

describe('CentralShopOrdersComponent', () => {
  let component: CentralShopOrdersComponent;
  let fixture: ComponentFixture<CentralShopOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CentralShopOrdersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CentralShopOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
