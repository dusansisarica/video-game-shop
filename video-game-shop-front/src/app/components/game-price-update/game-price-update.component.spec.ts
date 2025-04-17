import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GamePriceUpdateComponent } from './game-price-update.component';

describe('GamePriceUpdateComponent', () => {
  let component: GamePriceUpdateComponent;
  let fixture: ComponentFixture<GamePriceUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GamePriceUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GamePriceUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
