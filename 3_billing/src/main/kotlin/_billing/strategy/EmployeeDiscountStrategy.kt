package _billing.strategy

import _billing.*

class EmployeeDiscountStrategy: DiscountStrategy {
    private val employeeDiscount = 0.3

    override fun calculate(customer: Customer, bill: Bill): Double {
        return when(customer.customerType){
            CustomerType.EMPLOYEE ->{
                calculateBilling(bill)
            }
            else -> 0.0
        }
    }

    private fun calculateBilling(bill: Bill):Double{
        return bill.item.filter { it.productType != ProductType.GROCERIES }
                .map{ employeeDiscount * it.cost}.sum()
    }
}