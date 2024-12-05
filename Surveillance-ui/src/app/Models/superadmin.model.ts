import { User } from "./user.model";

export interface SuperAdmin extends User {
    // No additional fields, it just inherits from User
}