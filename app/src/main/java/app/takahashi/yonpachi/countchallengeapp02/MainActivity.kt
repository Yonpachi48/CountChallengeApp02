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

        // SoundPoolの設定
        mSoundPool = SoundPool.Builder().build()
        snd0 = mSoundPool.load(this, mSoundResource, 0)

        dataStore  = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        count = dataStore.getInt(COUNT_KEY, 0)
        setText()

        binding.plusButton.setOnClickListener{
            count += 1
            setText()
        }
    }


    override fun onResume() {
        super.onResume()

        mSoundPool = SoundPool.Builder().build()
        snd0 = mSoundPool.load(this, mSoundResource, 0)
    }

    override fun onPause() {

        val editor = dataStore.edit()
        editor.putInt(COUNT_KEY, count)
        editor.apply()
        Log.d(SHARED_PREFERENCES_NAME, "${dataStore.getInt(COUNT_KEY, 0)}")

        super.onPause()
    }


    // 呼び出されていない
    override fun onDestroy() {
        super.onDestroy()

        mSoundPool.release()

        val editor = dataStore.edit()
        editor.putInt(COUNT_KEY, count)
        editor.apply()
        Log.d(SHARED_PREFERENCES_NAME, "${dataStore.getInt(COUNT_KEY, 0)}")
    }


    private fun setText() {
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

    companion object {
        const val SHARED_PREFERENCES_NAME = "DataSource"
        const val COUNT_KEY = "count"
    }




}