package com.yappy.trnxd.backend.transaction.junior.library.utils;

import com.yappy.trnxd.backend.transaction.junior.library.model.ProfileDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class CommonHeaderUtils {

    public ProfileDTO getProfileDTO(HttpServletRequest headers) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setBankId(Optional.ofNullable(headers.getHeader("X-Bank-Id")).orElse(""));
        profileDTO.setUserId(Optional.ofNullable(headers.getHeader("X-User-Id")).orElse(""));
        profileDTO.setDate(LocalDateTime.now());
        profileDTO.setClientId(Optional.ofNullable(headers.getHeader("X-Client-Id")).orElse(""));
        profileDTO.setClientType(Optional.ofNullable(headers.getHeader("X-Client-Type")).orElse(""));

        return profileDTO;
    }
}
