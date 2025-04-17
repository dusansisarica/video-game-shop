import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent implements OnInit {

  @Input() reviews: any;
  @Input() totalNumber: any;
  @Input() user = false;
  @Input() button = false;
  @Input() status = false;
  @Input() delete = false;

  currentPage: number = 1;
  pageSize: number = 10;



  @Output() dataEvent = new EventEmitter<any>();
  @Output() dataUpdated = new EventEmitter<void>();



  constructor(private reviewService: ReviewService) { }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['reviews']) {
      console.log('Прегледи су ажурирани:', this.reviews);
      // Овде можете додати логику за ажурирање приказа
      this.reviews = this.reviews;
    }
  }

  showUser() {
    return this.user;
  }

  ngOnInit(): void {

  }

  showButton() {
    return this.button;
  }

  approve(id: any) {
    this.reviewService.approve(id).subscribe(() => {
      this.dataUpdated.emit();
    });
  }

  decline(id: any) {
    this.reviewService.decline(id).subscribe(() => {
      this.dataUpdated.emit();
    });
  }

  showStatus() {
    return this.status;
  }

  reviewStatus(review: any) {
    if (review.deleted == true) {
      return "Deleted"
    }
    if (review.approved == true) {
      return "Approved"
    }
    if (review.approved == false && review.denied == true) {
      return "Declined"
    }

    return "Pending"
  }

  showDelete() {
    if (localStorage.getItem("role") == "ROLE_ADMIN") {
      return this.delete;
    }
    return false;
  }

  deleteReview(id: any) {
    this.reviewService.delete(id).subscribe();
  }

  getStatusColor(review: any): string {
    if (review.deleted) {
      return 'default';
    }
    if (review.approved) {
      return 'green';
    }
    if (!review.approved && review.denied) {
      return 'red';
    }
    return 'gold'; // Za "Pending" status
  }
  onPageChange(page: any) {
    console.log(page);
    console.log('Getting data for page:', page);

    this.reviewService.getUserReviews(page).subscribe(data => {
      this.reviews = data.reviews;
      this.totalNumber = data.totalNumber;
    })
  }

}
