package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

interface FragmentListener // someone will listen to all event dispatched from fragment
{
    fun removeFragment()
}

class MyFragment : Fragment() {
    // you need a listener
    lateinit var listener: FragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentListener)
            listener = context // our listener is the context you need
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragLayout = inflater.inflate(R.layout.fragment_layout,
            container,
            false)

        val removeMeButton = fragLayout.findViewById<Button>(R.id.removeMeButton_Id)
        removeMeButton.setOnClickListener {
            listener.removeFragment()
        }


        return fragLayout
    }





    override fun onDetach() {
        super.onDetach()
    }
}
