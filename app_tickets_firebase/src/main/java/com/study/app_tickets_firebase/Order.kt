package layout

data class Order(
    val userName: String,
    val key: String,
    val allTickets: Int,
    val roundTrip: Int,
    val oneWay: Int,
    val total: Int )
