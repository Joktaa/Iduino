package fr.jorisrouziere.iduino.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import fr.jorisrouziere.iduino.R
import fr.jorisrouziere.iduino.databinding.ActivityMainBinding
import fr.jorisrouziere.iduino.favorite

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(fr.jorisrouziere.iduino.Map())

        binding.bottomNavigationView.setOnItemReselectedListener {

            when(it.itemId){

                R.id.list -> replaceFragment(fr.jorisrouziere.iduino.List())
                R.id.map -> replaceFragment(fr.jorisrouziere.iduino.Map())
                R.id.favoris -> replaceFragment(favorite())

                else ->{


                }

            }
            true

        }

    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()

    }
}