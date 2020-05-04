package com.kodorebi.mymovielist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.kodorebi.mymovielist.app.App
import com.kodorebi.mymovielist.db.domain.Db
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity() {

    private val db : Db by App.kodein.instance<Db>()

    private val navController: NavController by lazy {
        findNavController(R.id.navHost)
    }

    private var inExitTimeWindow = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController.setGraph(R.navigation.sign_in_nav_graph)
    }

    override fun onBackPressed() {
        if (navController.backStack.size <= 2 && !inExitTimeWindow) {
            Toast.makeText(
                this,
                "Press back again to leave the application",
                Toast.LENGTH_SHORT
            ).show()
            inExitTimeWindow = true
            GlobalScope.launch {
                delay(3000)
                inExitTimeWindow = false
            }
            return
        }

        super.onBackPressed()
    }
}
