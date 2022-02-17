package app.takahashi.yonpachi.countchallengeapp02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.takahashi.yonpachi.countchallengeapp02.databinding.ActivityMainBinding
import app.takahashi.yonpachi.countchallengeapp02.databinding.ActivityTitleBinding

class TitleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTitleBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        binding.toMainButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}