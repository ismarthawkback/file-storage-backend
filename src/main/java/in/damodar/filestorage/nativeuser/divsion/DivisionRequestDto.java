package in.damodar.filestorage.nativeuser.divsion;

import in.damodar.filestorage.validation.CreateValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DivisionRequestDto {
    private String name;
}
