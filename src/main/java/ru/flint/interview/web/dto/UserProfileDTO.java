package ru.flint.interview.web.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.flint.interview.util.annotation.NoHtml;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserProfileDTO extends AbstractDTO {

    @Size(max = 128)
    @NoHtml
    @NotBlank(message = "First name must not be empty")
    String firstName;

    @Size(max = 128)
    @NoHtml
    @NotBlank(message = "Last name must not be empty")
    String lastName;


    public UserProfileDTO(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
