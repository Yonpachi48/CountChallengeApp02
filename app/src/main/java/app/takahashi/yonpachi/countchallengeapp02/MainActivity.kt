package app.takahashi.yonpachi.countchallengeapp02

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.takahashi.yonpachi.countchallengeapp02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        var count: Int = 0
        binding.countText.text = count.toString()

        binding.plusButton.setOnClickListener{
            count += 1
            when((count+2)%2) {
                1 -> {
                    binding.countText.setTextColor(Color.RED)
                    binding.countText.text = count.toString()
                } else -> {
                    binding.countText.setTextColor(Color.BLUE)
                    binding.countText.text = count.toString()
                }
            }
        }

    }
}