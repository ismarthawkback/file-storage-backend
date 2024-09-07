package in.damodar.filestorage.nativeuser;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID>{
    User findByUsername(String username);
}
