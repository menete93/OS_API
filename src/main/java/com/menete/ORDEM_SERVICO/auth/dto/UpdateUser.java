package com.menete.ORDEM_SERVICO.auth.dto;

import java.util.UUID;

public record UpdateUser(UUID id,String username ,String newPassword,String oldPassword) {

}
