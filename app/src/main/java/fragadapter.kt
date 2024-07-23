import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.washmart.login
import com.example.washmart.timingfragment

class fragadapter (fa:FragmentActivity): FragmentStateAdapter(fa){
    override fun getItemCount(): Int {
      return 2
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0->timingfragment()
           1->login()
           else->timingfragment()
       }
    }
}