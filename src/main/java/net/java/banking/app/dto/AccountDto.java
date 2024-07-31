package net.java.banking.app.dto;

//Dto passes data from client to server


import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private String AccountHolderName;
    private double balance;



}
