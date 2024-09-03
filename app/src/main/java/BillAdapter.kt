
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.washmart.Bill
import com.example.washmart.R

class BillAdapter(context: Context, bills: List<Bill>) : ArrayAdapter<Bill>(context, R.layout.bill_item, bills) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.bill_item, parent, false)

        val bill = getItem(position) ?: return view
        Log.d("BillAdapter", "Bill at position $position: $bill")

        //ye bill_item ke ander ke textview ki id yaha declare kar raha hu
        val tvFinalTotal: TextView = view.findViewById(R.id.tvFinalTotal)
        val tvTotalCloths: TextView = view.findViewById(R.id.tvTotalCloths)
        val tvClothPrice: TextView = view.findViewById(R.id.tvClothPrice)
        val tvOrderDate: TextView = view.findViewById(R.id.tvOrderDate)
        val tvServiceCharge: TextView = view.findViewById(R.id.tvServiceCharge)
        val tvSelectedCloths: TextView = view.findViewById(R.id.tvSelectedCloths)
        val tvSelectedFabric: TextView = view.findViewById(R.id.tvSelectedFabric)
        val tvSelectedService: TextView = view.findViewById(R.id.tvSelectedService)
        val tvUserPhone: TextView = view.findViewById(R.id.tvUserPhone)
        val tvUserAddress: TextView = view.findViewById(R.id.tvUserAddress)
        val tvvUsername: TextView = view.findViewById(R.id.tvvUserName)

        //yaha in id ke andar bill ke table ko call kar raha hu
        tvFinalTotal.text = bill.finalTotal ?: "N/A"
        tvTotalCloths.text = bill.totalCloths ?: "N/A"
        tvClothPrice.text = bill.clothPrice ?: "N/A"
        tvOrderDate.text = bill.orderDate ?: "N/A"
        tvServiceCharge.text = bill.serviceCharge ?: "N/A"
        tvSelectedCloths.text = bill.selectedCloths ?: "N/A"
        tvSelectedFabric.text = bill.selectedFabric ?: "N/A"
        tvSelectedService.text = bill.selectedService ?: "N/A"
        tvUserPhone.text = bill.userPhone ?: "N/A"
        tvUserAddress.text = bill.userAddress ?: "N/A"
        tvvUsername.text = bill.username ?: "N/A"

        return view
    }
}
