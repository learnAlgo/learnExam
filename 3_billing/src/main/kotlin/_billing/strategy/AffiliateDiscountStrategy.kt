package _billing.strategy

import _billing.Bill
import _billing.Customer
import _billing.CustomerType
import _billing.ProductType

class AffiliateDiscountStrategy: DiscountStrategy {
    private val affiliateDiscount = 0.1

    override fun calculate(customer: Customer, bill: Bill): Double {
        return when(customer.customerType){
            CustomerType.AFFILIATE ->{
                calculateBilling(bill)
            }
            else -> 0.0
        }
    }

    private fun calculateBilling(bill: Bill):Double{
        return bill.item.filter { it.productType != ProductType.GROCERIES }
                .map{ affiliateDiscount * it.cost}.sum()
    }
}
