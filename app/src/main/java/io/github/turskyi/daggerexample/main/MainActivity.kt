package io.github.turskyi.daggerexample.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.github.turskyi.daggerexample.login.LoginActivity
import io.github.turskyi.daggerexample.registration.RegistrationActivity
import io.github.turskyi.daggerexample.settings.SettingsActivity
import io.github.turskyi.daggerexample.MyApplication
import io.github.turskyi.daggerexample.R
import io.github.turskyi.daggerexample.user.UserManager
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    /**
     * If the User is not registered, RegistrationActivity will be launched,
     * If the User is not logged in, LoginActivity will be launched,
     * else carry on with MainActivity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  Grab userManager from appComponent to check if the user is logged in or not
        val userManager = (application as MyApplication).appComponent.userManager()
        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                startActivity(Intent(this, RegistrationActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        } else {
            setContentView(R.layout.activity_main)
            /* Important: Doing conditional field injection (as we're doing in MainActivity.kt
            when injecting only if the user is logged in) is very dangerous.
            The developers have to be aware of the conditions and you risk getting
            NullPointerExceptions when interacting with injected fields.

To avoid this issue, we can add some indirection by creating a SplashScreen that routes
to either Registration, Login or Main depending on the state of the user.  */

            // If the MainActivity needs to be displayed, we get the UserComponent
            // from the application graph and gets this Activity injected
            userManager.userComponent!!.inject(this)
            setupViews()
        }
    }

    /**
     * Updating unread notifications onResume because they can get updated on SettingsActivity
     */
    override fun onResume() {
        super.onResume()
        findViewById<TextView>(R.id.notifications).text = mainViewModel.notificationsText
    }

    private fun setupViews() {
        findViewById<TextView>(R.id.hello).text = mainViewModel.welcomeText
        findViewById<Button>(R.id.settings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
