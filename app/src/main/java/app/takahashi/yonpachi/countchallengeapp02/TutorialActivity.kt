package app.takahashi.yonpachi.countchallengeapp02

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import app.takahashi.yonpachi.countchallengeapp02.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity(), ThirdFragment.ThirdListener {

     private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        binding.pager.adapter = PagerAdapter(this)
        binding.pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }


    private inner class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
         // ページ数を取得
         override fun getItemCount(): Int = NUM_PAGES

         // スワイプ位置によって表示するFragmentを変更
         override fun createFragment(position: Int): Fragment =
             when(position) {
                 0 -> FirstFragment()
                 1 -> SecondFragment()
                 2 -> ThirdFragment()
                 else -> FirstFragment()
             }
     }


    override fun onButtonClicked() {

        // 初回起動時
        val data = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = data.edit()
        editor.putBoolean(KEY_NAME, false)
        editor.apply()

        // TitleActivityに遷移
        val toTitleActivityIntent = Intent(this, TitleActivity::class.java)
        startActivity(toTitleActivityIntent)

        finish()
    }

    companion object {
        const val SHARED_PREFERENCES_NAME = "tutorialData"
        const val KEY_NAME = "count"
        const val NUM_PAGES = 3
    }
}