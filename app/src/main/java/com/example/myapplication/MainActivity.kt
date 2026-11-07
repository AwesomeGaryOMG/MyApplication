package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.UUID

class User() {
    lateinit var email: String
    val id = UUID.randomUUID()
    var name: String? = null

    constructor(email: String, name: String?) : this() {
        this.email = email
        this.name = name
    }

    constructor(email: String) : this() {
        this.email = email
    }

    override fun toString(): String {
        val nameIs = if (this.name != null) this.name else "EMPTY"

        val thisUser: String = """
            name is: $nameIs
            email is: ${this.email}
        """.trimIndent()

        return thisUser
    }
}

class MainActivity : AppCompatActivity(), FragmentListener {
    var users: Array<User> = arrayOf()

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


        loadFragButton.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragmentContainer_id,
                    MyFragment(),
                    "FragTag"
                ) // its unique name
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

        users = arrayOf(
            User("alpha@gmail.com"),
            User("beta@gmail.com", "Brown"),
            User("gamma@gmail.com"),
            User("delta@gmail.com", "Dan"),
            User("epsilon@gmail.com"),
            User("zeta@gmail.com", "Zoe"),
        )

    }

    fun loginPress(): View.OnClickListener {
        return View.OnClickListener {
            val builder = AlertDialog.Builder(this)

            builder.setTitle("Login here")
            builder.setMessage(("Enter your emaill address"))

            val inp = EditText(this)

            builder.setView(inp)
            builder.setPositiveButton("Log me in") { a, w ->
                login(inp.text.toString())
            }
            builder.setNegativeButton("Cancel") { a, w -> }

            builder.create().show()
        }
    }

    fun login(email: String) {
        for (usr: User in users) {
            if (usr.email == email) {
                // user was found
                //statusText.text = "Logged in Successfully"
                //statusText.setTextColor(Color.GREEN)
                return
            }
        }
        //statusText.text = "No Such User"
        //statusText.setTextColor(Color.RED)
    }

}

data class UserData(var name: String?, val email: String, val id: UUID = UUID.randomUUID())

//class User() {
//    var name: String? = null
//    lateinit var email: String
//    lateinit var id: UUID
//
//    constructor(myMap: HashMap<String, Any>) : this()
//    {
//        name = myMap["name"] as String?
//        email = myMap["email"] as String
//        if ((myMap["email"] != null)) email = myMap["email"] as String
//
//        myMap["id"]?.let{
//
//        }
//    }
//}