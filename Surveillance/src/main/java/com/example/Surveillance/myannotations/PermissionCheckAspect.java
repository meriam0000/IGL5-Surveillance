package com.example.Surveillance.myannotations;

import com.example.Surveillance.Entities.*;
import com.example.Surveillance.Exception.ForbiddenException;
import com.example.Surveillance.Exception.ResourceNotFoundException;
import com.example.Surveillance.Services.AdminDepartementService;
import com.example.Surveillance.Services.AdminEtablissementService;
import com.example.Surveillance.Services.SuperAdminService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class PermissionCheckAspect {

    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private AdminDepartementService adminDepartementService;

    @Autowired
    private AdminEtablissementService adminEtablissementService;

    @Before("@annotation(requiresPermissions)")
    public void checkPermission(RequiresPermissions requiresPermissions) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // Get the HTTP method and use it as the action
        String action = request.getMethod();
        if (action.equals("PUT")) {
            action = "UPDATE";
        }

        // Map the resource based on the request URI
        String resource = mapRequestUriToResource(request.getRequestURI());
        System.out.println(resource + "===" + resource.substring(1, 2).toUpperCase());
        System.out.println(action);

        Claims claims = (Claims) request.getAttribute("claims");
        if (claims == null || !hasRequiredPermission(claims, resource, action)) {
            throw new ForbiddenException("You do not have the required permissions to access this resource.");
        }
    }

    private String mapRequestUriToResource(String requestUri) {

        if (requestUri.startsWith("/api/superAdmins")) {
            return "/superAdmin";
        }
        if (requestUri.startsWith("/api/adminDepartements")) {
            return "/adminDepartement";
        }
        if (requestUri.startsWith("/api/adminEtablissements")) {
            return "/adminEtablissement";
        }
        if (requestUri.startsWith("/api/permissions")) {
            return "permissions";
        }
        return requestUri;
    }

    private boolean hasRequiredPermission(Claims claims, String resource, String action) {
        String username = claims.getSubject();

        // Check for SuperAdmin
        SuperAdmin superAdmin = superAdminService.getSuperAdminByUsername(username);
        if (superAdmin != null) {
            return checkPermissions(superAdmin.getPermissionGroup(), resource, action);
        }

        // Check for AdminDepartement
        AdminDepartement adminDepartement = adminDepartementService.getAdminDepartementByUsername(username);
        if (adminDepartement != null) {
            return checkPermissions(adminDepartement.getPermissionGroup(), resource, action);
        }

        // Check for AdminEtablissement
        AdminEtablissement adminEtablissement = adminEtablissementService.getAdminEtablissementByUsername(username);
        if (adminEtablissement != null) {
            return checkPermissions(adminEtablissement.getPermissionGroup(), resource, action);
        }

        throw new ResourceNotFoundException("User not found.");
    }

    private boolean checkPermissions(PermissionGroup permissionGroup, String resource, String action) {
        if (permissionGroup != null) {
            String permissionName = action.charAt(0) + "_" + resource.substring(1, 2).toUpperCase();
            for (Permission permission : permissionGroup.getPermissions()) {
                if (permission.getPermissionName().equals(permissionName)) {
                    return true;
                }
            }
        }
        throw new ForbiddenException("You do not have the required permissions to access this resource.");
    }
}
