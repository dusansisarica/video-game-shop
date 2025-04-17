import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { DiscountService } from 'src/app/services/discount.service';
import { FeaturedGamesService } from 'src/app/services/featured-games.service';
import { PriceService } from 'src/app/services/price.service';


@Component({
  selector: 'app-game-price-update',
  templateUrl: './game-price-update.component.html',
  styleUrls: ['./game-price-update.component.css']
})
export class GamePriceUpdateComponent implements OnInit {


  @Input() gameId: any;
  validateForm!: UntypedFormGroup;
  price: any;

  constructor(private fb: UntypedFormBuilder, private discountService: DiscountService, private priceService: PriceService, private videoGameService: FeaturedGamesService) {
    // this.validateForm = this.fb.group({
    //   updateType: ['priceAndDiscount'],
    //   discountType: [null],
    //   price: [null],
    //   priceRange: [null],
    //   discountValidityRange: [null],
    //   discountValue: [null],
    // });
  }

  ngOnInit(): void {
    this.getPrice();
    this.initForm();
  }

  initForm(): void {
    this.validateForm = this.fb.group({
      updateType: ['discountOnly'],
      discountType: [null],
      price: [null],
      priceValidityRange: [null],
      discountValidityRange: [null],
      discountValue: [null],
    });
    this.validateForm.get('updateType')?.valueChanges.subscribe(value => {
      this.updateDateRangeValidation(value);
    });

    // Пратите промене у пољу priceValidityRange
    this.validateForm.get('priceValidityRange')?.valueChanges.subscribe(() => {
      this.updateDateRangeValidation(this.validateForm.get('updateType')?.value);
    });
  }

  updateDateRangeValidation(updateType: string): void {
    const discountRangeControl = this.validateForm.get('discountValidityRange');

    if (updateType === 'discountOnly') {
      // Ограничите датуме на основу this.price
      discountRangeControl?.setValidators([
        Validators.required,
        (control) => this.dateRangeValidator(control, this.price?.startDate, this.price?.endDate)
      ]);
    } else if (updateType === 'priceAndDiscount') {
      const priceRange = this.validateForm.get('priceValidityRange')?.value;
      if (priceRange && priceRange[0] && priceRange[1]) {
        // Ograničite datume na osnovu priceValidityRange
        discountRangeControl?.setValidators([
          Validators.required,
          (control) => this.dateRangeValidator(control, priceRange[0], priceRange[1])
        ]);
      } else {
        discountRangeControl?.setValidators(Validators.required);
      }
    }

    discountRangeControl?.updateValueAndValidity();
  }

  dateRangeValidator(control: AbstractControl, startDate: Date, endDate: Date): { [key: string]: any } | null {
    if (!control.value) {
      return null;
    }
    const [rangeStart, rangeEnd] = control.value;
    if (rangeStart < startDate || rangeEnd > endDate) {
      return { 'dateRangeInvalid': true };
    }
    return null;
  }

  disabledDate = (current: Date): boolean => {
    if (!this.price) {
      return false; // Ако цена није учитана, дозволи све датуме
    }
    if (this.validateForm.get('updateType')?.value === 'discountOnly' && this.price) {
      // Онемогућите датуме изван опсега this.price
      // return new Date(current).getTime() < new Date(this.price.startDate).getTime()
      //   || new Date(current).getTime() > new Date(this.price.endDate).getTime();
      console.log(this.price.startDate + this.price.endDate)
      return current < this.price.startDate || current > this.price.endDate;
    } else if (this.validateForm.get('updateType')?.value === 'priceAndDiscount') {
      const priceRange = this.validateForm.get('priceValidityRange')?.value;
      if (priceRange && priceRange[0] && priceRange[1]) {
        // Онемогућите датуме изван опсега priceValidityRange
        return new Date(current).getTime() < new Date(priceRange[0]).getTime()
          || new Date(current).getTime() > new Date(priceRange[1]).getTime();
      }
    }
    return false;
  };

  submitForm() {
    if (this.validateForm.valid) {
      console.log(this.validateForm.value);
      if (this.validateForm.value.updateType === 'discountOnly') {
        const discount = {
          name: this.validateForm.value.discountType,
          actionType: this.validateForm.value.discountType,
          discountValue: this.validateForm.value.discountValue,
          startDate: this.validateForm.value.discountValidityRange[0],
          endDate: this.validateForm.value.discountValidityRange[1],
        }
        this.discountService.addDiscount(discount, this.gameId).subscribe();

      } else if (this.validateForm.value.updateType === 'priceAndDiscount') {
        let startDate: Date | null = null;
        let endDate: Date | null = null;
        if (this.validateForm.value.discountValidityRange) {
          [startDate, endDate] = this.validateForm.value.discountValidityRange;
          const price = {
            price: this.validateForm.value.price,
            startDate: this.validateForm.value.priceValidityRange[0],
            endDate: this.validateForm.value.priceValidityRange[1],
            action: {
              name: this.validateForm.value.discountType,
              actionType: this.validateForm.value.discountType,
              discountValue: this.validateForm.value.discountValue,
              startDate: startDate,
              endDate: endDate,
            }
          }
          this.priceService.makePrice(price, this.gameId).subscribe();
        }
        else {
          const price = {
            price: this.validateForm.value.price,
            startDate: this.validateForm.value.priceValidityRange[0],
            endDate: this.validateForm.value.priceValidityRange[1],
            action: {
              name: this.validateForm.value.discountType,
              actionType: null,
              discountValue: null,
              startDate: null,
              endDate: null,
            }
          }
          this.priceService.makePrice(price, this.gameId).subscribe();

        }

        console.log('Form is not valid');
      }

    }

  }

  getPrice() {
    this.videoGameService.getGameById(this.gameId).subscribe(
      (game: any) => {
        this.price = game.price;
        // Осигурајте да су датуми правилно парсирани
        if (this.price) {
          this.price.startDate = new Date(this.price.startDate);
          this.price.endDate = new Date(this.price.endDate);
        }
        this.updateDateRangeValidation(this.validateForm.get('updateType')?.value);
      },
      (error: any) => {
        console.error('Greška pri dobijanju cene igre:', error);
      }
    );
  }



}
