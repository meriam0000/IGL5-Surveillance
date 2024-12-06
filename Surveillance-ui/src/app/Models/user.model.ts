import { Role } from "./role.model";

export interface User {
    id: number;
    firstname: string;
    fullName: string;
    email: string;
    password: string;
    role: Role;
    authorities: string[];
    username: string;
    accountNonExpired: boolean;
    accountNonLocked: boolean;
    credentialsNonExpired: boolean;
    enabled: boolean;
}
