import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CentralInventoryComponent } from './central-inventory.component';

describe('CentralInventoryComponent', () => {
  let component: CentralInventoryComponent;
  let fixture: ComponentFixture<CentralInventoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CentralInventoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CentralInventoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
