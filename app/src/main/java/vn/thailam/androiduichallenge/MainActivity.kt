package vn.thailam.androiduichallenge

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import vn.thailam.challenge1.Challenge1Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        btnChallenge1.setOnClickListener {
            val intent = Intent(this, Challenge1Activity::class.java)
            startActivity(intent)
        }
    }
}