package com.gaindesat.ddp.models;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_partner")
public class Partner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String partName;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private Set<User> users;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private Set<MissionData> missionData;

    public Set<MissionData> getMissionData() {
        return missionData;
    }

    public void setMissionData(Set<MissionData> missionData) {
        this.missionData = missionData;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
