package inc.epitome.codingsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductListAdapter(
    private val products: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private val productPriceUpdates = mutableListOf<Pair<String, Double>>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView
        val priceView: TextView

        init {
            nameView = view.findViewById(R.id.nameView)
            priceView = view.findViewById(R.id.priceView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product = products[position]
        val price = productPriceUpdates.firstOrNull { it.first == product.id }?.second ?: 0.0
        viewHolder.nameView.text = product.name
        viewHolder.priceView.text = String.format("%.2f €", price)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun refreshList(updatedList: List<Pair<String, Double>>) {
        productPriceUpdates.clear()
        productPriceUpdates.addAll(updatedList)
        notifyDataSetChanged()
    }

}