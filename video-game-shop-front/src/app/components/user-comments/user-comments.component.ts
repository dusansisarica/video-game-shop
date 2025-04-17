import { Component, OnInit } from '@angular/core';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-user-comments',
  templateUrl: './user-comments.component.html',
  styleUrls: ['./user-comments.component.css']
})
export class UserCommentsComponent implements OnInit {

  reviews?: any;
  totalNumber: any;
  currentPage: number = 1;

  constructor(private reviewService: ReviewService) { }

  ngOnInit(): void {
    this.getReviews();
  }

  getReviews(page: number = 1) {
    this.reviewService.getUserReviews(page).subscribe(data => {
      this.reviews = data.reviews;
      this.totalNumber = data.totalNumber;
      console.log(this.totalNumber);
    });
  }

  changePage(page: number): void {
    this.currentPage = page;
    this.getReviews(page);
  }

}
