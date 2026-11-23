import { Component, inject } from '@angular/core';
import { ExempleService } from '../exemple-service';

@Component({
  imports: [],
  selector: 'app-exemple-page',
  styleUrl: './exemple-page.scss',
  templateUrl: './exemple-page.html',
})
export class ExemplePage {
  private exemplesService = inject(ExempleService);
}
