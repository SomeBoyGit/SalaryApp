package ru.someboy.salaryApplication.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RepairSum {
    private Integer countRepairs;

    private Integer repairsRevenueSum;

    private Integer repairsPurchaseSum;

    private Integer repairsProfitSum;

    private Double salarySum;

    public RepairSum(Integer countRepairs, Integer repairsRevenueSum, Integer repairsPurchaseSum, Integer repairsProfitSum, Double salarySum) {
        this.countRepairs = countRepairs;
        this.repairsRevenueSum = repairsRevenueSum;
        this.repairsPurchaseSum = repairsPurchaseSum;
        this.repairsProfitSum = repairsProfitSum;
        this.salarySum = salarySum;
    }

    public RepairSum getRepairSum(List<Repair> repairs, Employee employee) {
        RepairSum repairSum = createRepairSumEmpty();
        if (repairs != null) {
            long employeeId = employee.getId();
            AtomicInteger count = new AtomicInteger();
            repairs.forEach(repair -> {
                if (repair.getSeller().getId() == employeeId || repair.getMaster().getId() == employeeId) {
                    repairSum.setCountRepairs(count.incrementAndGet());
                    repairSum.setRepairsRevenueSum(repairSum.getRepairsRevenueSum() + repair.getRepairsRevenue());
                    repairSum.setRepairsPurchaseSum(repairSum.getRepairsPurchaseSum() + repair.getRepairsPurchase());
                    repairSum.setRepairsProfitSum(repairSum.getRepairsProfitSum() + repair.getRepairsProfit());
                    if (repair.getSeller().getId() == employeeId) {
                        repairSum.setSalarySum(repairSum.getSalarySum() + repair.getSellerSalary());
                    }
                    if (repair.getMaster().getId() == employeeId) {
                        repairSum.setSalarySum(repairSum.getSalarySum() + repair.getMasterSalary());
                    }
                }
            });
        }
        return repairSum;
    }

    private RepairSum createRepairSumEmpty() {
        return new RepairSum(
                0, 0, 0, 0, 0d
        );
    }

    public RepairSum getRepairSum2(List<Repair> repairs, Employee employee) {
        RepairSum repairSum = createRepairSumEmpty();

        repairs.forEach(repair -> System.err.println("repair: " + repair));
        if (repairs != null) {
            long employeeId = employee.getId();
            AtomicInteger count = new AtomicInteger();
            repairs.forEach(repair -> {
                repairSum.setCountRepairs(count.incrementAndGet());
                repairSum.setRepairsRevenueSum(repairSum.getRepairsRevenueSum() + repair.getRepairsRevenue());
                repairSum.setRepairsPurchaseSum(repairSum.getRepairsPurchaseSum() + repair.getRepairsPurchase());
                repairSum.setRepairsProfitSum(repairSum.getRepairsProfitSum() + repair.getRepairsProfit());
                if (repair.getSeller().getId() == employeeId) {
                    repairSum.setSalarySum(repairSum.getSalarySum() + repair.getSellerSalary());
                }
                if (repair.getMaster().getId() == employeeId) {
                    repairSum.setSalarySum(repairSum.getSalarySum() + repair.getMasterSalary());
                }
            });
        }
        return repairSum;
    }
}
