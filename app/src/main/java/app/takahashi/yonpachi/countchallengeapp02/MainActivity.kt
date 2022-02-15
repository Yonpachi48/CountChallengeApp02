package app.takahashi.yonpachi.countchallengeapp02

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import app.takahashi.yonpachi.countchallengeapp02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // 数字のインスタンス
    var count: Int = 0
    // SoundPool関連のインスタンス
    private lateinit var mSoundPool: SoundPool
    var mSoundResource = R.raw.system39
    var snd0: Int = 0
    // "DataStore"という名前でインスタンスを生成
    private lateinit var dataStore: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        dataStore  = getSharedPreferences("DataStore", Context.MODE_PRIVATE)
        count = dataStore.getInt("COUNT", 0)
        binding.countText.text = count.toString()

        binding.plusButton.setOnClickListener{
            count += 1
            changeTextColor(count)
        }
    }


    override fun onResume() {
        super.onResume()

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        mSoundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(1)
            .build()

        snd0 = mSoundPool.load(applicationContext, mSoundResource, 0)
    }


    override fun onDestroy() {
        super.onDestroy()
        mSoundPool.release()

        val editor = dataStore.edit()
        editor.putInt("COUNT", count)
        editor.apply()
        Log.d("saveData", "${dataStore.getInt("COUNT", 0)}")
    }


    private fun changeTextColor(count: Int) {
        when((count+2)%2) {
            1 -> {
                binding.countText.setTextColor(Color.RED)
                binding.countText.text = count.toString()
                mSoundPool.play(snd0, 1.0F, 1.0F, 0, 0, 1.0F)
            } else -> {
            binding.countText.setTextColor(Color.BLUE)
            binding.countText.text = count.toString()
            }
        }
    }




}