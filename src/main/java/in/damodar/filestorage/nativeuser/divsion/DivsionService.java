package in.damodar.filestorage.nativeuser.divsion;

import org.springframework.stereotype.Service;

import in.damodar.filestorage.nativeuser.Divsion;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DivsionService {

    private final DivisionRepository divisionRepository;
    
    public void createDivision(String name) {
        if(name != null && name.length() > 0) {
            Divsion d = new Divsion();
            d.setName(name);
            divisionRepository.save(d);
        }
    }
}
