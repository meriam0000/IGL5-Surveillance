import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-action-button',
  templateUrl: './action-button.component.html',
  styleUrls: ['./action-button.component.css']
})
export class ActionButtonComponent {
  @Input() bgColor: string = 'white';
  @Input() svgSrc: string = 'assets/plus-lg.svg';
  @Input() text: string = 'Ajouter';
  @Output() buttonClick = new EventEmitter<void>();

  onClick() {
    this.buttonClick.emit();
  }
}
