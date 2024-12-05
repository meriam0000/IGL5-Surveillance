import { Role } from "./role.model";
import { Token } from "./token.model";

export interface User {
    id: number;
    firstname: string;
    fullName: string;
    email: string;
    password: string;
    role: Role;
    tokens: Token[];
    authorities: string[];
    username: string;
    accountNonExpired: boolean;
    accountNonLocked: boolean;
    credentialsNonExpired: boolean;
    enabled: boolean;
}