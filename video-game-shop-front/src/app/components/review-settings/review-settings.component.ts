import { Component, OnInit } from '@angular/core';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-review-settings',
  templateUrl: './review-settings.component.html',
  styleUrls: ['./review-settings.component.css']
})
export class ReviewSettingsComponent implements OnInit {

  reviews: any;
  constructor(private reviewService: ReviewService) { }

  ngOnInit(): void {
    this.getReviews();
  }

  getReviews() {
    this.reviewService.getReviewsForApproval().subscribe(data => {
      this.reviews = data.reviews;
    })
    console.log("odradjeno")
  }

}
