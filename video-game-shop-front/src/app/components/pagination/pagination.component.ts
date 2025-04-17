import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {
  @Input() totalNumber : any;
  @Input() currentPage : any;
  @Output() dataEvent = new EventEmitter<any>();


  constructor() { }

  ngOnInit(): void {
  }

  changePage(page : any){
    this.dataEvent.emit(page);
  }

}
