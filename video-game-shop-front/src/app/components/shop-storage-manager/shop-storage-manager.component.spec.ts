import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopStorageManagerComponent } from './shop-storage-manager.component';

describe('ShopStorageManagerComponent', () => {
  let component: ShopStorageManagerComponent;
  let fixture: ComponentFixture<ShopStorageManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopStorageManagerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShopStorageManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
