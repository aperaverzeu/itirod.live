package lt.skarb.backend.model.dto;

import javax.validation.constraints.*;

public record UserDTO(@NotBlank @NotNull @Min(value = 4) @Max(value = 32) String username,
                      @NotBlank @NotNull @Email String email,
                      @NotBlank @NotNull @Min(value = 8) String password) {}
