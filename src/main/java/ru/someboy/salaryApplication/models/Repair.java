package ru.someboy.salaryApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "repair")
@Getter
@Setter
@NoArgsConstructor
//@ToString
public class Repair {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "phone_number")
    @Size(min = 10,max = 15, message = "Номер телефона должен содержать от 10 до 15 символов")
    private String phoneNumber;

    @Column(name = "repairs_revenue")
    private Integer repairsRevenue;

    @Column(name = "repairs_purchase")
    private Integer repairsPurchase;

    @Column(name = "repairs_profit")
    private Double repairsProfit;

    @Column(name = "seller_salary")
    private Double sellerSalary;

    @Column(name = "master_salary")
    private Double masterSalary;

    @NotNull
    @Column(name = "date_of_acceptance")
    private LocalDateTime dateOfAcceptance;

    @Column(name = "date_of_done")
    private LocalDateTime dateOfDone;

    @Column(name = "date_of_issue")
    private LocalDateTime dateOfIssue;

    @Column(name = "is_done")
    private Boolean isDone;

    @NotNull
    @Column(name = "annotation")
    @Size(max = 300, message = "Аннотация должна содержать не более 300 символов")
    private String annotation;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Employee seller;

    @ManyToOne
    @JoinColumn(name = "master_id", referencedColumnName = "id")
    private Employee master;

//    @NotNull
    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    public Repair(String phoneNumber, Integer repairsRevenue, Integer repairsPurchase, Double repairsProfit,
                  Double sellerSalary, Double masterSalary, LocalDateTime dateOfAcceptance,
                  LocalDateTime dateOfDone, LocalDateTime dateOfIssue, Boolean isDone, String annotation,
                  Employee employee, Employee seller, Employee master, Shop shop) {
        this.phoneNumber = phoneNumber;
        this.repairsRevenue = repairsRevenue;
        this.repairsPurchase = repairsPurchase;
        this.repairsProfit = repairsProfit;
        this.sellerSalary = sellerSalary;
        this.masterSalary = masterSalary;
        this.dateOfAcceptance = dateOfAcceptance;
        this.dateOfDone = dateOfDone;
        this.dateOfIssue = dateOfIssue;
        this.isDone = isDone;
        this.annotation = annotation;
        this.employee = employee;
        this.seller = seller;
        this.master = master;
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", repairsRevenue=" + repairsRevenue +
                ", repairsPurchase=" + repairsPurchase +
                ", repairsProfit=" + repairsProfit +
                ", sellerSalary=" + sellerSalary +
                ", masterSalary=" + masterSalary +
                ", dateOfAcceptance=" + dateOfAcceptance +
                ", dateOfDone=" + dateOfDone +
                ", dateOfIssue=" + dateOfIssue +
                ", isDone=" + isDone +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
