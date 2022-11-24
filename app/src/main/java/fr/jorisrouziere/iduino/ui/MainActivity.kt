package fr.jorisrouziere.iduino.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.jorisrouziere.iduino.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}