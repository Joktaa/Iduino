package fr.jorisrouziere.iduino.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.jorisrouziere.iduino.R
import fr.jorisrouziere.iduino.utils.ApiUtils


// Implement OnMapReadyCallback.
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO : Dans l'écran de chargement
        //ApiUtils.get();
    }
}