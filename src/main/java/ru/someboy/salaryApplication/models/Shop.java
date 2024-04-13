package ru.someboy.salaryApplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "shop")
@Getter
@Setter
@NoArgsConstructor
//@ToString
public class Shop {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name")
    @Size(min = 3, max = 30, message = "Название должно содержать от 3 до 30 символов")
    private String name;

    @NotNull
    @Column(name = "rental")
    private Integer rental;

    @NotNull
    @Column(name = "accessories_percent")
    private Integer accessoriesPercent;

    @NotNull
    @Column(name = "bonus_depends")
    private Integer bonusDepends;

    @NotNull
    @Column(name = "bonus_amount")
    private Integer bonusAmount;

    @NotNull
    @Column(name = "marketing_margin_percent")
    private Integer marketingMarginPercent;

    @NotNull
    @Column(name = "sim_percent")
    private Integer simPercent;

    @NotNull
    @Column(name = "devices_percent")
    private Integer devicesPercent;

    @NotNull
    @Column(name = "minimum_wage")
    private Integer minimumWage;

    @NotNull
    @Column(name = "is_work")
    private Boolean isWork;

    @NotNull
    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @ManyToMany
    @JoinTable(
            name = "shop_employee",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;

    @JsonIgnore
    @OneToMany(mappedBy = "shop")
    private List<Data> data;

    @JsonIgnore
    @OneToMany(mappedBy = "shop")
    private List<Repair> repairs;

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rental=" + rental +
                ", accessoriesPercent=" + accessoriesPercent +
                ", marketingMarginPercent=" + marketingMarginPercent +
                ", simPercent=" + simPercent +
                ", devicesPercent=" + devicesPercent +
                ", isWork=" + isWork +
                ", dateOfRegistration=" + dateOfRegistration +
                ", employees=" + employees +
                ", data=" + data +
                ", repairs=" + repairs +
                '}';
    }
}
