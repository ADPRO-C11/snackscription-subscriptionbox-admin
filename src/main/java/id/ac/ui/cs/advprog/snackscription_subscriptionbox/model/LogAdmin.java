package id.ac.ui.cs.advprog.snackscription_subscriptionbox.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "Log")
@Entity
@NoArgsConstructor
public class LogAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int id;

    @Column(name = "log_string")
    private String logString;

    @Column(name = "log_sub_box")
    private String subBoxId;

    @Column
    private LocalDateTime date;

    public LogAdmin(String logString, String subBoxId) {
        this.logString = logString;
        this.subBoxId = subBoxId;
        this.date = LocalDateTime.now();
    }
}
