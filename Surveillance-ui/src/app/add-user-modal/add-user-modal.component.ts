import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-add-user-modal',
  templateUrl: './add-user-modal.component.html',
  styleUrls: ['./add-user-modal.component.css']
})
export class AddUserModalComponent {
  @Output() closeModal = new EventEmitter<void>();

  firstName: string = '';
  fullName: string = '';
  email: string = '';

  onClose(): void {
    this.closeModal.emit();
  }

  onSubmit(): void {
    // Log the user details with the new fields
    console.log(`Adding Super Admin: ${this.firstName} ${this.fullName}, Email: ${this.email}`);
    this.onClose();  // Close the modal after submitting
  }
}
