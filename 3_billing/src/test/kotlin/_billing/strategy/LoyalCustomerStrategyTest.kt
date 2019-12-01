package _billing.strategy

import _billing.*
import org.junit.Assert.*
import org.junit.Test

class LoyalCustomerStrategyTest {
    @Test
    fun should_give_discount_to_loyal(){
        val cutomer = Customer(CustomerType.AFFILIATE, 100)
        val bill = Bill(listOf(BillingItem(ProductType.GROCERIES, 100.0),
                BillingItem(ProductType.NON_GROCERIES, 1000.0)))

        val strategy = LoyalCustomerStrategy()
        val result = strategy.calculate(cutomer, bill)
        assertEquals(50.0, result,0.0001)
    }

    @Test
    fun should_NOT_give_discount_to_non_loyal(){
        val cutomer = Customer(CustomerType.EMPLOYEE, 1)
        val bill = Bill(listOf(BillingItem(ProductType.GROCERIES, 100.0),
                BillingItem(ProductType.NON_GROCERIES, 1000.0)))

        val strategy = AffiliateDiscountStrategy()
        val result = strategy.calculate(cutomer, bill)
        assertEquals(0.0, result,0.0001)
    }

}
