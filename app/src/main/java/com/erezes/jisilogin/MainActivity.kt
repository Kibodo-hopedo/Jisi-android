package com.erezes.jisilogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.erezes.jisilogin.databinding.MainPageBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { MainPageBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val phLogInBtn : Button = binding.phLogIn
        phLogInBtn.setOnClickListener {
            val logInPage = Intent(this, LogInPage::class.java)
            startActivity(logInPage)
        }
        val signInBtn : Button = binding.signInBtn
        signInBtn.setOnClickListener {
            val signInPage = Intent(this, SignInPage::class.java)
            startActivity (signInPage)
        }
    }
}