package vn.thailam.challenge1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vn.thailam.challenge1.features.list.ChequeListFragment

class Challenge1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge1)
        addChequeListFragment()
    }

    private fun addChequeListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer, ChequeListFragment.newInstance())
            .commit()
    }
}
