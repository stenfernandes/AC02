package com.example.espacobemestar

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_mapa.*
import java.util.jar.Manifest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapaFragment : Fragment(), OnMapReadyCallback {

    private var map:GoogleMap? = null

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        this.map= googleMap

        val ok = PermissionsUtils.validate(activity!!, 1,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
        map?.isMyLocationEnabled = ok

        var location = LatLng(-23.5426978, -46.4111268)
        val update = CameraUpdateFactory.newLatLngZoom(location, 18f)
        map?.moveCamera(update)

        map?.addMarker(MarkerOptions().title("Espaço Bem Estar").snippet("Espaço Bem Estar Massoterapia").position(location))

        map?.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (result in grantResults){
            if (result == PackageManager.PERMISSION_GRANTED)
                map?.isMyLocationEnabled = true
                return
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_mapa, container, false)
        val mapaFragment = childFragmentManager.findFragmentById(R.id.mapaFragment) as SupportMapFragment

        mapaFragment.getMapAsync(this)
        return view

    }

}
