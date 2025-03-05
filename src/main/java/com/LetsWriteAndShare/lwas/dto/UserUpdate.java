package com.LetsWriteAndShare.lwas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdate(@NotBlank(message = "{LetsWriteAndShare.constraint.username.notblank}")
                         @Size(min = 4, max = 255, message = "{LetsWriteAndShare.constraint.username.size}")
                         String username) {
}
