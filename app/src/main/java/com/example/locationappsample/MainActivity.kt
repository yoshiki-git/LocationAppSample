package com.example.locationappsample

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.locationappsample.databinding.ActivityMainBinding
import com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE : Int = 1000
    private val TAG ="MainActivity.kt"

    private lateinit var binding: ActivityMainBinding

    private val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //permissionチェック
        checkPermission(permissions,REQUEST_CODE)
/*
        if(isGooglePlayServicesAvailable(this)){
            
        }

 */

        binding.btnMeasure.setOnClickListener {

        }

    }

    //Permissionチェックのメソッド
    fun checkPermission(permissions: Array<String>?, request_code: Int) {
        // 許可されていないものだけダイアログが表示される
        ActivityCompat.requestPermissions(this, permissions!!, request_code)
    }

    // requestPermissionsのコールバック
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                var i = 0
                while (i < permissions.size) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        /*     Toast toast = Toast.makeText(this,
                                "Added Permission: " + permissions[i], Toast.LENGTH_SHORT);
                        toast.show(); */
                        Log.d(TAG,permissions[i]+"is granted")
                    } else {
                        val toast = Toast.makeText(this,
                            "設定より権限をオンにした後、アプリを再起動してください", Toast.LENGTH_LONG)
                        toast.show()
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        //Fragmentの場合はgetContext().getPackageName()
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                    i++
                }
            }
            else -> {
            }
        }
    }
}