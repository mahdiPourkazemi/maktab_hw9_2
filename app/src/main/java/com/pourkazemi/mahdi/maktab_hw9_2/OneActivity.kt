package com.pourkazemi.mahdi.maktab_hw9_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.pourkazemi.mahdi.maktab_hw9_2.databinding.ActivityOneBinding

private var cheat:Boolean=false
class OneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOneBinding.inflate(layoutInflater)
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
                putExtra("quz",1)
            })
        }
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this,SecondActivity::class.java))
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