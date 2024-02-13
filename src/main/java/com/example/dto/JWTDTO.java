package com.example.dto;

import com.example.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTDTO {
    private Integer id;
    private String email;
    private ProfileRole role;

    public JWTDTO(Integer id) {
        this.id = id;
    }

    public JWTDTO(Integer id, ProfileRole role) {
        this.id = id;
        this.role = role;
    }

    public JWTDTO(String email, ProfileRole role) {
        this.email = email;
        this.role = role;
    }
}
