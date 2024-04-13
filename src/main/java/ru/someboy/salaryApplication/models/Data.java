package ru.someboy.salaryApplication.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "data")
@Getter
@Setter
@NoArgsConstructor
//@ToString
public class Data {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accessories_count")
    private Integer accessoriesCount;

    @Column(name = "accessories_revenue")
    private Integer accessoriesRevenue;

    @Column(name = "accessories_salary")
    private Double accessoriesSalary;

    @Column(name = "bonus")
    private Integer bonus;

    @Column(name = "marketing_margin")
    private Integer marketingMargin;

    @Column(name = "marketing_margin_profit")
    private Double marketingMarginProfit;

    @Column(name = "marketing_margin_salary")
    private Double marketingMarginSalary;

    @Column(name = "sim_count")
    private Integer simCount;

    @Column(name = "sim_revenue")
    private Integer simRevenue;

    @Column(name = "sim_salary")
    private Double simSalary;

    @Column(name = "devices_count")
    private Integer devicesCount;

    @Column(name = "devices_revenue")
    private Integer devicesRevenue;

    @Column(name = "devices_purchase")
    private Integer devicesPurchase;

    @Column(name = "devices_profit")
    private Integer devicesProfit;

    @Column(name = "devices_salary")
    private Double devicesSalary;

    @Column(name = "repairs_count")
    private Integer repairsCount;

    @Column(name = "repairs_revenue")
    private Integer repairsRevenue;

    @Column(name = "repairs_purchase")
    private Integer repairsPurchase;

    @Column(name = "repairs_profit")
    private Integer repairsProfit;

    @Column(name = "repairs_salary")
    private Double repairsSalary;

    @Column(name = "date_of_entry")
    private LocalDateTime dateOfEntry;

    @Column(name = "date_of_data")
    private LocalDateTime dateOfData;

    @Column(name = "expenses")
    private Integer expenses;

    @Column(name = "annotation")
    @Size(max = 300, message = "Аннотация не должна содержать более 300 символов")
    private String annotation;

    @Column(name = "cash")
    private Integer cash;

    @Column(name = "cashless")
    private Integer cashless;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "all_revenue")
    private Double allRevenue;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    @Transient
    private List<Repair> repairs;

    public Data(Integer accessoriesCount, Integer accessoriesRevenue, Double accessoriesSalary, Integer bonus,
                Integer marketingMargin, Double marketingMarginProfit, Double marketingMarginSalary, Integer simCount,
                Integer simRevenue, Double simSalary, Integer devicesCount, Integer devicesRevenue,
                Integer devicesPurchase, Integer devicesProfit, Double devicesSalary, Integer repairsCount,
                Integer repairsRevenue, Integer repairsPurchase, Integer repairsProfit, Double repairsSalary,
                LocalDateTime dateOfEntry, LocalDateTime dateOfData, Integer expenses, String annotation, Integer cash,
                Integer cashless, Double salary, Double allRevenue) {
        this.accessoriesCount = accessoriesCount;
        this.accessoriesRevenue = accessoriesRevenue;
        this.accessoriesSalary = accessoriesSalary;
        this.bonus = bonus;
        this.marketingMargin = marketingMargin;
        this.marketingMarginProfit = marketingMarginProfit;
        this.marketingMarginSalary = marketingMarginSalary;
        this.simCount = simCount;
        this.simRevenue = simRevenue;
        this.simSalary = simSalary;
        this.devicesCount = devicesCount;
        this.devicesRevenue = devicesRevenue;
        this.devicesPurchase = devicesPurchase;
        this.devicesProfit = devicesProfit;
        this.devicesSalary = devicesSalary;
        this.repairsCount = repairsCount;
        this.repairsRevenue = repairsRevenue;
        this.repairsPurchase = repairsPurchase;
        this.repairsProfit = repairsProfit;
        this.repairsSalary = repairsSalary;
        this.dateOfEntry = dateOfEntry;
        this.dateOfData = dateOfData;
        this.expenses = expenses;
        this.annotation = annotation;
        this.cash = cash;
        this.cashless = cashless;
        this.salary = salary;
        this.allRevenue = allRevenue;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", accessoriesCount=" + accessoriesCount +
                ", accessoriesRevenue=" + accessoriesRevenue +
                ", accessoriesSalary=" + accessoriesSalary +
                ", bonus=" + bonus +
                ", marketingMargin=" + marketingMargin +
                ", marketingMarginProfit=" + marketingMarginProfit +
                ", marketingMarginSalary=" + marketingMarginSalary +
                ", simCount=" + simCount +
                ", simRevenue=" + simRevenue +
                ", simSalary=" + simSalary +
                ", devicesCount=" + devicesCount +
                ", devicesRevenue=" + devicesRevenue +
                ", devicesPurchase=" + devicesPurchase +
                ", devicesProfit=" + devicesProfit +
                ", devicesSalary=" + devicesSalary +
                ", repairsCount=" + repairsCount +
                ", repairsRevenue=" + repairsRevenue +
                ", repairsPurchase=" + repairsPurchase +
                ", repairsProfit=" + repairsProfit +
                ", repairsSalary=" + repairsSalary +
                ", dateOfEntry=" + dateOfEntry +
                ", dateOfData=" + dateOfData +
                ", expenses=" + expenses +
                ", annotation='" + annotation + '\'' +
                ", cash=" + cash +
                ", cashless=" + cashless +
                ", salary=" + salary +
                ", employee=" + employee.getUsername() +
                ", shop=" + shop.getName() +
                ", allRevenue=" + allRevenue +
//                ", repairs=" + repairs.get(1) +
                '}';
    }
}
