import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserEmployModalComponent } from './user-employ-modal.component';

describe('UserEmployModalComponent', () => {
  let component: UserEmployModalComponent;
  let fixture: ComponentFixture<UserEmployModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserEmployModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserEmployModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
