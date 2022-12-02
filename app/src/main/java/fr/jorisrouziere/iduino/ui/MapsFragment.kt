package fr.jorisrouziere.iduino.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import fr.jorisrouziere.iduino.R
import fr.jorisrouziere.iduino.model.Bar


class MapsFragment : Fragment() {
    companion object {
        lateinit var bars: ArrayList<Bar>
    }

    private var locationArrayList: ArrayList<LatLng>? = null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        Log.d("bars : ", bars.toString())
        for (i in locationArrayList!!.indices) {

            googleMap.addMarker(MarkerOptions().position(locationArrayList!![i]).title("Marker"))
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList!![i]))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment?.getMapAsync(callback)

        locationArrayList = ArrayList()

        for (i in bars.indices) {

            locationArrayList!!.add(LatLng(bars[i].lat, bars[i].lon))
        }

    }
}
