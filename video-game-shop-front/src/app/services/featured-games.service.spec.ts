import { TestBed } from '@angular/core/testing';

import { FeaturedGamesService } from './featured-games.service';

describe('FeaturedGamesService', () => {
  let service: FeaturedGamesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FeaturedGamesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
