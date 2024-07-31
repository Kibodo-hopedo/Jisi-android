package com.erezes.jisilogin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import back_button
import com.erezes.jisilogin.databinding.LogInPageBinding

class LogInPage : AppCompatActivity() {
    private val binding by lazy { LogInPageBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val logToPhone : Button = binding.logInBtn
        val id : EditText = binding.numInput
        val password = binding.passwordInput
        val backLayout : FrameLayout = binding.back
        supportFragmentManager.commit{
            replace(backLayout.id, back_button())
        }
        logToPhone.setOnClickListener {
            if (id.text.length > 5 && password.text.toString().length > 7){
                val phoneNumberPage = Intent(this, LogInSuccess::class.java)
                startActivity(phoneNumberPage)
                finish()
            }
        }
        binding.numInput.addTextChangedListener(PhoneNumberFormattingTextWatcher("KR"))
        val passwordInput : EditText = binding.passwordInput
        passwordInput.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            }
            return@setOnKeyListener false
        }
    }
}