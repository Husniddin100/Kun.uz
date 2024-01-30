package com.example.util;

import com.example.dto.JWTDTO;
import com.example.enums.ProfileRole;
import com.example.exp.ForbiddenExeption;
import jakarta.servlet.http.HttpServletRequest;

public class HttpRequestUtil {
    public static Integer getProfileId(HttpServletRequest request, ProfileRole... requiredList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");

        for (ProfileRole requiredRole : requiredList) {
            if (role.equals(requiredRole)) {
                return id;
            }
        }
        throw new ForbiddenExeption("Method not allowed: ");
    }

    public static Integer getJWTDTO(HttpServletRequest request, ProfileRole... requiredRoleList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        JWTDTO dto = new JWTDTO(id, role);
        if (requiredRoleList.length==0){
            return id;
        }

        for (ProfileRole requiredRole : requiredRoleList) {
            if (role.equals(requiredRole)) {
                return dto.getId();
            }
        }
        throw new ForbiddenExeption("Method not allowed");
    }
}
