package _billing.strategy

import _billing.*
import org.junit.Assert.*
import org.junit.Test


class EmployeeDiscountStrategyTest{
    @Test
    fun should_give_discount_to_employee(){
        val cutomer = Customer(CustomerType.EMPLOYEE, 100)
        val bill = Bill(listOf(BillingItem(ProductType.GROCERIES, 100.0),
                BillingItem(ProductType.NON_GROCERIES, 1000.0)))

        val strategy = EmployeeDiscountStrategy()
        val result = strategy.calculate(cutomer, bill)
        assertEquals(300.0, result,0.0001)
    }

    @Test
    fun should_NOT_give_discount_to_non_employee(){
        val cutomer = Customer(CustomerType.GENERAL, 100)
        val bill = Bill(listOf(BillingItem(ProductType.GROCERIES, 100.0),
                BillingItem(ProductType.NON_GROCERIES, 1000.0)))

        val strategy = EmployeeDiscountStrategy()
        val result = strategy.calculate(cutomer, bill)
        assertEquals(0.0, result,0.0001)
    }

}
