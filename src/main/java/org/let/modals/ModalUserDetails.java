package org.let.modals;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="USER_DETAILS")
@Getter
@Setter
public class ModalUserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id", nullable = false)
    Long recordId;

    @Column(name = "User_Details_id", nullable = false)
    Long userDetailId;

    @Column(name = "User_Real_Name", nullable = false)
    String userRealName;

    @Column(name = "User_Real_Mid_Name", nullable = false)
    String userRealMidName;

    @Column(name = "User_Profile_Pic", nullable = false)
    String userProfilePic;

    @Column(name = "User_Cover_Pic", nullable = false)
    String userCoverPic;



}
