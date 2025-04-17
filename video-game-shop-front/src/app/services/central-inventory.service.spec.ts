import { TestBed } from '@angular/core/testing';

import { CentralInventoryService } from './central-inventory.service';

describe('CentralInventoryService', () => {
  let service: CentralInventoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CentralInventoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
