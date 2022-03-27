package com.pourkazemi.mahdi.maktab_hw9_2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.pourkazemi.mahdi.maktab_hw9_2.databinding.ActivitySevenBinding

private var cheat:Boolean=false
class SevenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySevenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySevenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cheatedActivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                cheat = it.data?.getBooleanExtra("isCheated", false)?:false
                Toast.makeText(this, "your cheating attribute is $cheat", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnCheat.setOnClickListener {
            cheatedActivityResult.launch(Intent(this, CheatActivity::class.java).apply {
                putExtra("quz",7)
            })
        }
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this,EightActivity::class.java))
        }
        binding.btnTrue.setOnClickListener {
            Toast.makeText(this, "you are RIGHT", Toast.LENGTH_SHORT).show()
            binding.btnTrue.visibility= View.INVISIBLE
            binding.btnFalse.visibility= View.INVISIBLE
        }
        binding.btnFalse.setOnClickListener {
            Toast.makeText(this, "you are FALSE", Toast.LENGTH_SHORT).show()
            binding.btnFalse.visibility= View.INVISIBLE
            binding.btnTrue.visibility= View.INVISIBLE
        }
        binding.btnPre.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putBoolean("cheat",cheat)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Toast.makeText(this, "${savedInstanceState.getBoolean("cheat")}"
            , Toast.LENGTH_SHORT).show()
        super.onRestoreInstanceState(savedInstanceState)
    }
}
