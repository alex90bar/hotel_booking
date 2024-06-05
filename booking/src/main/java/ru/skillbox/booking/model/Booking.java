package ru.skillbox.booking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

/**
 * Booking
 *
 * @author alex90bar
 */

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dt_start", nullable = false)
    private LocalDate dtStart;

    @Column(name = "dt_end", nullable = false)
    private LocalDate dtEnd;

    @Exclude
    @JoinColumn(name = "room")
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @Exclude
    @JoinColumn(name = "`user`")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}