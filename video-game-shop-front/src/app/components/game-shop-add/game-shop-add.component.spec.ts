import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameShopAddComponent } from './game-shop-add.component';

describe('GameShopAddComponent', () => {
  let component: GameShopAddComponent;
  let fixture: ComponentFixture<GameShopAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GameShopAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameShopAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
