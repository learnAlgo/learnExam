package _billing.strategy

import _billing.Bill
import _billing.Customer

class TotalBillingDiscountStrategy:DiscountStrategy{
    private val discountPer100 = 5.0
    override fun calculate(customer: Customer, bill: Bill): Double {
       val sum = bill.item.sumByDouble { it.cost }
       return calculateDiscount(sum)
    }

    private fun calculateDiscount(sum:Double):Double{
        val multiplier = Math.floor( sum / 100 )
        return multiplier * discountPer100
    }
}