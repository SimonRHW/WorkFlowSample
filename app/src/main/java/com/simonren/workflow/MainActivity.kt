package com.simonren.workflow

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.simonren.workflow.databinding.ContentMainBinding
import com.simonren.workflow.workmanager.ColdStartWorkManagerUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ContentMainBinding
    lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        }
        super.onCreate(savedInstanceState)
        binding = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ColdStartWorkManagerUtil.startWorkFlow(this)

    }

}