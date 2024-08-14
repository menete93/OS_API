package com.menete.ORDEM_SERVICO.auth.dto;

import com.menete.ORDEM_SERVICO.auth.entity.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
