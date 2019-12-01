package _billing

import _billing.strategy.DiscountStrategy

interface Cashier {
    fun getTotalAmount(customer: Customer, bill: Bill): Double
}

class CashierImpl(val listDiscount: List<DiscountStrategy>) : Cashier {
    override fun getTotalAmount(customer: Customer, bill: Bill): Double {
        val discount = listDiscount.map { it.calculate(customer, bill) }.max() ?: 0.0
        val sumAllItem = bill.item.sumByDouble { it.cost }
        return sumAllItem - discount
    }

}