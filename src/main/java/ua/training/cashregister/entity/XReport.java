package ua.training.cashregister.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class XReport {
    private long id_x_report;

    private LocalDateTime shiftStart;

    private LocalDateTime time;

    private Set<Receipt> receipts = new HashSet<>();
}
