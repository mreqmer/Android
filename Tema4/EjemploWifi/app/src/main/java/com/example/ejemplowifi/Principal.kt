package com.example.ejemplowifi

import android.R
import android.bluetooth.BluetoothManager
import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.nfc.NfcManager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext






@Composable
fun principalView(){

    val context: Context = LocalContext.current.applicationContext
    val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val nfcManager = context.getSystemService(Context.NFC_SERVICE) as NfcManager
    val gpsManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isWifiEnabled = wifiManager.isWifiEnabled
    val bluetoothAdapter = bluetoothManager.adapter
    val nfcAdapter = nfcManager.defaultAdapter
    val isGpsEnabled = gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val isConnectivity = connectivityManager.isActiveNetworkMetered



    Column(
        modifier = Modifier
            .fillMaxSize(),


        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ){
        Text(text = "Wifi: ${isWifiEnabled}")
        Text(text = "Datos moviles: ${isConnectivity}")
        Text(text = "Bluetooth: ${bluetoothAdapter.isEnabled}")
        Text(text = "NFC: ${nfcAdapter.isEnabled}")
        Text(text = "GPS: ${isGpsEnabled}")


    }

}