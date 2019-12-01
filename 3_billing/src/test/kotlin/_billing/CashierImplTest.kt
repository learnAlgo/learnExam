package _billing

import _billing.strategy.DiscountStrategy
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.*
import org.junit.Test

class CashierImplTest{
    @Test
    fun should_have_highest_discount(){
        val lowDiscount = mock<DiscountStrategy>{
            on{this.calculate(any(),any())}.thenReturn(5.0)
        }

        val mediumDiscount = mock<DiscountStrategy>{
            on{this.calculate(any(),any())}.thenReturn(10.0)
        }

        val highDiscount = mock<DiscountStrategy>{
            on{this.calculate(any(),any())}.thenReturn(100.0)
        }

        val listDiscount = listOf(lowDiscount,mediumDiscount,highDiscount)

        val cashier = CashierImpl(listDiscount)

        val customer = Customer(CustomerType.GENERAL, 100)
        val bill = Bill(listOf(BillingItem(ProductType.GROCERIES, 1000.0)))

        val result = cashier.getTotalAmount(customer, bill)

        assertEquals(900.0,result, 0.01 )
    }

}