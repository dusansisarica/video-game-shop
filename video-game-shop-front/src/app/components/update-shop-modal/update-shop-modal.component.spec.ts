import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateShopModalComponent } from './update-shop-modal.component';

describe('UpdateShopModalComponent', () => {
  let component: UpdateShopModalComponent;
  let fixture: ComponentFixture<UpdateShopModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateShopModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateShopModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
