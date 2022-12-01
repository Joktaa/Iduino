package fr.jorisrouziere.iduino.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.jorisrouziere.iduino.R


// Implement OnMapReadyCallback.
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}