import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ExemplePage } from './exemple/exemple-page/exemple-page';

@Component({
  imports: [RouterOutlet, ExemplePage],
  selector: 'app-root',
  styleUrl: './app.scss',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('front');
}
