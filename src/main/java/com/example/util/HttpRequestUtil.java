package com.example.util;


import com.example.dto.JWTDTO;
import com.example.enums.ProfileRole;
import com.example.exp.AppBadException;
import jakarta.servlet.http.HttpServletRequest;

public class HttpRequestUtil {
    public static Integer getProfileId(HttpServletRequest request, ProfileRole... requiredRoleList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");

        if (requiredRoleList.length == 0) {
            return id;
        }
        for (ProfileRole requiredRole : requiredRoleList) {
            if (role.equals(requiredRole)) {
                return id;
            }
        }
        throw new AppBadException("Method not allowed");
    }

    public static JWTDTO getJWTDTO(HttpServletRequest request, ProfileRole... requiredRoleList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        JWTDTO dto = new JWTDTO(id, role);
        for (ProfileRole requiredRole : requiredRoleList) {
            if (role.equals(requiredRole)) {
                return dto;
            }
        }
        throw new AppBadException("Method not allowed");
    }
}
