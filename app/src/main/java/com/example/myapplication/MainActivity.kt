package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Global variable
var signedUser = false;
class MainActivity : AppCompatActivity(), FragmentListener
{
    override fun removeFragment() // required function
    {
        val myFrag = supportFragmentManager.findFragmentByTag("FragTag")
        if (myFrag != null) {
            supportFragmentManager
                .beginTransaction()
                .remove(myFrag)
                .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val fragContainer = findViewById<FrameLayout>(R.id.fragmentContainer_id)
        val loadFragButton = findViewById<Button>(R.id.loadFragButton_id)
        loadFragButton.setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer_id,
                    MyFragment(),
                    "FragTag") // its unique name
                .commit()
        }

//       findViewById<Button>(R.id.removeFragButton_id).setOnClickListener {
//           val myFrag = supportFragmentManager.findFragmentByTag("FragTag")
//           if (myFrag != null) {
//               supportFragmentManager
//                   .beginTransaction()
//                   .remove(myFrag)
//                   .commit()
//           }
//       }
    }


}