package fr.jorisrouziere.iduino.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import fr.jorisrouziere.iduino.R
import fr.jorisrouziere.iduino.databinding.ActivityMainBinding
import fr.jorisrouziere.iduino.utils.ApiUtils


// Implement OnMapReadyCallback.
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NavBarInclude.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.favorite -> replaceFragment(FavorisFragment())
                R.id.list -> replaceFragment(MainFragment())
                R.id.map -> replaceFragment(MapsFragment())

                else ->{


                }
            }
            true
        }

        // binding.NavBarInclude.bottomNavigationView.visibility = View.INVISIBLE

    }



    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}