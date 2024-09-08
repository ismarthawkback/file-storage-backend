package in.damodar.filestorage.nativeuser.divsion;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;

import in.damodar.filestorage.validation.CreateValidation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/division")
@RequiredArgsConstructor
public class DivisionController {

    private final DivsionService divsionService;

    @PostMapping("/add")
    public ResponseEntity<String> postMethodName(@ModelAttribute DivisionRequestDto requestDto) {
        System.out.println(requestDto);
        divsionService.createDivision(requestDto.getName());
        return ResponseEntity.ok().body("Division with name : " + requestDto.getName() + " is Created");
    }
    
}
