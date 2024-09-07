package in.damodar.filestorage.nativeuser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name = "divsions")
public class Divsion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;


    @ElementCollection(targetClass = ContentTypes.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "division_content_types", joinColumns = @JoinColumn(name = "division_id"))
    @Column(name = "content_type")
    private Set<ContentTypes> contentTypes = new HashSet<>();
    // private Set<ContentTypes> contentTypes = new HashSet<>();

    @OneToMany(cascade = CascadeType.DETACH,
    mappedBy = "division",
    fetch = FetchType.LAZY,
    orphanRemoval = false, 
    targetEntity = User.class)
    private Set<User> users = new HashSet<>();
}
