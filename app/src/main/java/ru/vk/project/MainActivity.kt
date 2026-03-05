package ru.vk.project

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.vk.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val model by lazy {
        MainModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.openSecondActivityBtn.setOnClickListener {
            model.dispatchIntent(
                MainIntent.OpenSecondActivityIntent(
                    binding.msgEditText.text.toString(),
                    applicationContext
                )
            )
        }

        binding.shareTextBtn.setOnClickListener {
            model.dispatchIntent(
                MainIntent.ShareTextIntent(
                    binding.shareEditText.text.toString(),
                    applicationContext
                )
            )
        }

        binding.callFriendBtn.setOnClickListener {
            model.dispatchIntent(
                MainIntent.CallFriendIntent(
                    binding.telEditText.text.toString(),
                    applicationContext
                )
            )
        }
    }
}