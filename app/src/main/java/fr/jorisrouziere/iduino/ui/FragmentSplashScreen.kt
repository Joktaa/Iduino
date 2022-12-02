package fr.jorisrouziere.iduino.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.jorisrouziere.iduino.R
import fr.jorisrouziere.iduino.model.Bar
import fr.jorisrouziere.iduino.utils.API
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class FragmentSplashScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_splash_screen, container, false)

        Thread {
            val bars = ArrayList<Bar>()
            var array: JSONArray? = null
            val api = API.getInstance()
            try {
                val j = JSONObject(api.bars)
                array = j.getJSONArray("records")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            var obj: JSONObject? = null
            var fields: JSONObject? = null
            var bar: Bar
            for (i in 0 until array!!.length()) {
                try {
                    obj = array!!.getJSONObject(i)
                    fields = obj.getJSONObject("fields")
                } catch (e: JSONException) {
                    e.printStackTrace()
                    continue
                }
                bar = Bar()
                try {
                    bar.id = obj.getString("recordid")
                } catch (ignored: JSONException) {
                }
                try {
                    bar.name = fields.getString("name")
                } catch (ignored: JSONException) {
                }
                try {
                    bar.phone = fields.getString("phone")
                } catch (ignored: JSONException) {
                }
                try {
                    bar.lat = (fields.getJSONArray("geo_point_2d")[0] as Double)!!
                } catch (ignored: JSONException) {
                }
                try {
                    bar.lon = (fields.getJSONArray("geo_point_2d")[1] as Double)!!
                } catch (ignored: JSONException) {
                }
                bars.add(bar)
            }

            MainFragment.bars = bars

            Handler(Looper.getMainLooper()).post {
                findNavController().navigate(R.id.action_fragmentSplashScreen2_to_mainFragment)
            }

        }.start()

        return view
    }


}