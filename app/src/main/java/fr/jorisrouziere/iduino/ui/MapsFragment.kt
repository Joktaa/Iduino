package fr.jorisrouziere.iduino.ui

import android.os.Bundle
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


class MapsFragment : Fragment() {
    private var sydney = LatLng(-34.00, 151.00)
    private var TamWorth = LatLng(-31.083332, 150.916672)
    private var NewCastle = LatLng(-32.916668, 151.750000)
    private var Brisbane = LatLng(-27.470125, 153.021072)


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

        locationArrayList!!.add(sydney)
        locationArrayList!!.add(TamWorth);
        locationArrayList!!.add(NewCastle);
        locationArrayList!!.add(Brisbane);
    }
}

