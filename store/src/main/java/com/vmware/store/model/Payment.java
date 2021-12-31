package com.vmware.store.model;
import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String expDate;
    private String number;

    public Payment() {
    }

    public Payment(String name, String expDate, String number){
        this.number = number;
        this.expDate = expDate;
        this.name = name;
    }

}