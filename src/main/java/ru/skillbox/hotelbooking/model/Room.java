package ru.skillbox.hotelbooking.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

/**
 * Room
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
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "people_capacity", nullable = false)
    private Integer peopleCapacity;

    @Column(name = "unavailable_dates")
    private String unavailableDates;

    @Exclude
    @JoinColumn(name = "hotel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

}
