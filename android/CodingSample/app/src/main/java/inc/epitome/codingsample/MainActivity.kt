package inc.epitome.codingsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var prices = mutableListOf<Pair<String, Double>>()

    var repository = ProductPriceUpdateRepository()

    var products = mutableListOf(
        Product(id = "127123", name = "Apple Smartphone"),
        Product(id = "322345", name = "Asus Laptop"),
        Product(id = "546546", name = "Intel Laptop"),
        Product(id = "341345", name = "Samsung Smartphone"),
        Product(id = "432465", name = "Huawei Smartphone"),
        Product(id = "234234", name = "Xiaomi Smartphone")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productListAdapter = ProductListAdapter(products)
        findViewById<RecyclerView>(R.id.product_list).adapter = productListAdapter

        products.forEach { product ->
            val priceUpdate = repository.getPriceWithTax(id = product.id)
            val price = priceUpdate.currentPrice
            val index = prices.indexOfFirst { it.first == priceUpdate.id }
            if (index != -1) {
                prices[index] = priceUpdate.id to price
            } else {
                prices.add(priceUpdate.id to price)
            }
            productListAdapter.refreshList(prices.toList())
        }
    }

}