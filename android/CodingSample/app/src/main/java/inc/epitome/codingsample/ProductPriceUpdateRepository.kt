package inc.epitome.codingsample

import kotlin.random.Random

class ProductPriceUpdateRepository {

    fun getPriceWithTax(id: String): ProductPriceUpdate {
        val taxRatio = (Random.nextDouble(from = 0.01, until = 0.10))
        val productPrice = (Random.nextInt(from = 20, until = 200))
        val currentPrice = productPrice + (taxRatio * productPrice)
        return ProductPriceUpdate(id = id, currentPrice = currentPrice)
    }

}