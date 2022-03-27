package com.pourkazemi.mahdi.maktab_hw9_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pourkazemi.mahdi.maktab_hw9_2.databinding.ActivityCheatBinding

private var isCheated = false

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quzNum = getIntent().getIntExtra("quz", -1)
        binding.btnCheated.setOnClickListener {
            isCheated = true
            binding.tvAnswer.text = "your quz $quzNum answer is TRUE"

        }
    }

    override fun onBackPressed() {
        val mIntent = Intent().apply {
            putExtra("isCheated", isCheated)
            //setResult(RESULT_OK,this)
        }
        setResult(RESULT_OK, mIntent)

        super.onBackPressed()
    }
}