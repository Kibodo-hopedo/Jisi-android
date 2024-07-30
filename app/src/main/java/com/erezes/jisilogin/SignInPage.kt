package com.erezes.jisilogin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import back_button
import com.erezes.jisilogin.databinding.SignInPageBinding


class SignInPage : AppCompatActivity() {
    private val binding by lazy { SignInPageBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportFragmentManager.commit{
            replace(binding.back.id, back_button())
        }
        binding.phInput.addTextChangedListener(PhoneNumberFormattingTextWatcher("KR"))
        val phInputKt : EditText = binding.phInput
        phInputKt.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            }
            return@setOnKeyListener false
        }

        binding.goBtn.setOnClickListener {
            if (phInputKt.length() >= 13) {
                startActivity(Intent(this, SignInSuccess::class.java))
            }
        }
    }
}