import { TestBed } from '@angular/core/testing';
import { CanActivateFn, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth/auth.service';
import { AuthGuard } from './auth.guard';

// Mock the AuthService
class MockAuthService {
  isLoggedIn() {
    return true; // Adjust logic as needed
  }
}

// Mock the Router
class MockRouter {
  navigate = jasmine.createSpy('navigate'); // Spy on router navigate method
}

describe('authGuard', () => {
  let authGuard: AuthGuard;
  let authService: AuthService;
  let router: Router;
  const executeGuard: CanActivateFn = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ) => TestBed.runInInjectionContext(() => authGuard.canActivate(route, state));

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        AuthGuard,
        { provide: AuthService, useClass: MockAuthService },
        { provide: Router, useClass: MockRouter }
      ]
    });

    authGuard = TestBed.inject(AuthGuard);
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);
  });

  it('should be created', () => {
    expect(authGuard).toBeTruthy();
  });

  it('should return true if user is logged in', () => {
    spyOn(authService, 'isLoggedIn').and.returnValue(true);

    const canActivateResult = executeGuard(new ActivatedRouteSnapshot(), new RouterStateSnapshot());

    expect(canActivateResult).toBeTrue();
    expect(router.navigate).not.toHaveBeenCalled(); // No redirection expected for logged-in users
  });

  it('should redirect to /login if user is not logged in', () => {
    spyOn(authService, 'isLoggedIn').and.returnValue(false);

    const canActivateResult = executeGuard(new ActivatedRouteSnapshot(), new RouterStateSnapshot());

    expect(canActivateResult).toBeFalse();
    expect(router.navigate).toHaveBeenCalledWith(['/login']); // Redirection expected
  });
});
