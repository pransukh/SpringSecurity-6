package org.let.modals;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="USER_DATABASE")
@Getter
@Setter
public class ModalUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id", nullable = false )
    Long recordId;

    @Column(name = "user_id", nullable = false )
    Long userId;

    @Column(name = "user_name", nullable = false )
    String userName;

    @Column(name = "password", nullable = false )
    String password;



}
