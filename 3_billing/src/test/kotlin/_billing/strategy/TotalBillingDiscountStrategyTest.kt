package _billing.strategy

import _billing.*
import org.junit.Assert.*
import org.junit.Test

class TotalBillingDiscountStrategyTest{
    @Test
    fun should_calculate_total_billing(){
        val customer = Customer(CustomerType.GENERAL, 3)
        val bill = Bill( listOf(BillingItem(ProductType.GROCERIES, 500.0),
                BillingItem(ProductType.NON_GROCERIES, 500.0)))

        val discount = TotalBillingDiscountStrategy().calculate(customer, bill)
        assertEquals(50.0, discount, 0.01)
    }
}