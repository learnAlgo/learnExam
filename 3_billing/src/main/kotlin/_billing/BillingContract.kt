package _billing

enum class ProductType {
    GROCERIES, NON_GROCERIES
}

enum class CustomerType {
    GENERAL,
    AFFILIATE,
    EMPLOYEE
}


data class BillingItem(val productType: ProductType, val cost: Double)
data class Customer(val customerType: CustomerType, val periodCustomer: Int)
data class Bill(val item: List<BillingItem>)