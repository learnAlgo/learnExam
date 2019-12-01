package _billing.strategy

import _billing.Bill
import _billing.Customer
import _billing.ProductType

class LoyalCustomerStrategy:DiscountStrategy{
    private val loyalCustomerDiscount = 0.05

    override fun calculate(customer: Customer, bill: Bill): Double {
        return when{
            customer.periodCustomer >= 2 -> calculateAffiliateDiscount(bill)
            else -> 0.0
        }
    }

    private fun calculateAffiliateDiscount(bill:Bill):Double{
        return bill.item.filter { it.productType != ProductType.GROCERIES }
                .map{ loyalCustomerDiscount * it.cost}.sum()
    }
}