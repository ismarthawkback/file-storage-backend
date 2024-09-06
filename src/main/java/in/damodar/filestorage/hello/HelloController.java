package in.damodar.filestorage.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("")
public class HelloController {
    @GetMapping("/hello")
    public ResponseEntity<String> getMethodName() {
        return ResponseEntity.ok().body("Hello World !");
    }
    
}
