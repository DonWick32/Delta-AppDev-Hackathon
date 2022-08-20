package com.example.megaapi


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.R


class TabFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // adding the layout with inflater
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_dynamic, container, false)
        initViews(view)
        return view
    }

    // initialise the categories
    private fun initViews(view: View) {
        val textView = view.findViewById<TextView>(R.id.commonTextView)
        textView.text = "Category :  " + arguments!!.getInt("position")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    // pause function call
    override fun onPause() {
        super.onPause()
    }

    // resume function call
    override fun onResume() {
        super.onResume()
    }

    // stop when we close
    override fun onStop() {
        super.onStop()
    }

    // destroy the view
    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        fun newInstance(): TabFragment {
            return TabFragment()
        }
    }
}