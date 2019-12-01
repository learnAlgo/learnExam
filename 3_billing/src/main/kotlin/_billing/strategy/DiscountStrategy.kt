package _billing.strategy

import _billing.Bill
import _billing.Customer

interface DiscountStrategy{
    fun calculate(customer: Customer, bill: Bill):Double
}

