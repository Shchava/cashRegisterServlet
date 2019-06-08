package ua.training.cashregister.entity;


import java.time.LocalDateTime;

public class Receipt {
    private long id_receipt;
    private LocalDateTime created;
    private User cashier;
    private User seniorCashier;
}
