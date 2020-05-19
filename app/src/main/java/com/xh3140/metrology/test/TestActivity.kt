package com.xh3140.metrology.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.xh3140.metrology.R
import com.xh3140.metrology.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private var binding: ActivityTestBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)

        setContentView(R.layout.activity_test)

    }
}
