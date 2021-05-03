package io.github.turskyi.daggerexample.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import io.github.turskyi.daggerexample.main.MainActivity
import io.github.turskyi.daggerexample.registration.RegistrationActivity
import io.github.turskyi.daggerexample.MyApplication
import io.github.turskyi.daggerexample.R
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    // 1) LoginViewModel is provided by Dagger
    @Inject
    lateinit var loginViewModel: LoginViewModel
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        // 2) Creates an instance of Login component by grabbing the factory from the app graph
        // and injects this activity to that Component
        (application as MyApplication).appComponent.loginComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // listens for the loginState LiveData
        loginViewModel.loginState.observe(this, { state ->
            when (state) {
                is LoginSuccess -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is LoginError -> errorTextView.visibility = View.VISIBLE
            }
        })

        errorTextView = findViewById(R.id.error)
        setupViews()
    }

    private fun setupViews() {
        val usernameEditText = findViewById<EditText>(R.id.username)
        usernameEditText.isEnabled = false
        usernameEditText.setText(loginViewModel.getUsername())

        val passwordEditText = findViewById<EditText>(R.id.password)
        passwordEditText.doOnTextChanged { _, _, _, _ -> errorTextView.visibility = View.INVISIBLE }

        findViewById<Button>(R.id.login).setOnClickListener {
            loginViewModel.login(usernameEditText.text.toString(), passwordEditText.text.toString())
        }
        findViewById<Button>(R.id.unregister).setOnClickListener {
            loginViewModel.unregister()
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}

sealed class LoginViewState
object LoginSuccess : LoginViewState()
object LoginError : LoginViewState()
