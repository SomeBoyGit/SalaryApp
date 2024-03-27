package ru.someboy.salaryApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "repair")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Repair {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "phone_number")
    @Size(min = 10,max = 15, message = "Номер телефона должен содержать от 10 до 15 символов")
    private String phoneNumber;

    @NotNull
    @Column(name = "repairs_count")
    private Integer repairsCount;

    @NotNull
    @Column(name = "repairs_revenue")
    private Integer repairsRevenue;

    @NotNull
    @Column(name = "repairs_purchase")
    private Integer repairsPurchase;

    @NotNull
    @Column(name = "repairs_profit")
    private Double repairsProfit;

    @NotNull
    @Column(name = "repairs_salary")
    private Double repairsSalary;

    @NotNull
    @Column(name = "date_of_entry")
    private LocalDateTime dateOfEntry;

    @NotNull
    @Column(name = "date_of_repair")
    private LocalDateTime dateOfRepair;

    @NotNull
    @Column(name = "annotation")
    @Size(max = 300, message = "Аннотация должна содержать не более 300 символов")
    private String annotation;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Employee seller;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private Employee master;
}
