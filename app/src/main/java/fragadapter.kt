import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.washmart.login
import com.example.washmart.pickup2
import com.example.washmart.timingfragment

class fragadapter (fa:FragmentActivity): FragmentStateAdapter(fa){
    override fun getItemCount(): Int {
      return 3
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0->timingfragment()
           1->pickup2()
           2->login()
           else->timingfragment()
       }
    }
}