package com.example.megaapi


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*

import androidx.annotation.Nullable;
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment;


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
        val view: View = inflater.inflate(R.layout.fragment_tab, container, false)
        initViews(view)
        return view
    }

    // initialise the categories
    private fun initViews(view: View) {
        val inputUrl = view.findViewById<EditText>(R.id.inputUrl)
        val txtUrl = view.findViewById<TextView>(R.id.textUrl)
        val inputHeader = view.findViewById<EditText>(R.id.inputHeader)
        val inputQuery = view.findViewById<EditText>(R.id.inputQuery)
        val inputQueryValue = view.findViewById<EditText>(R.id.inputQueryValue)
        val btnHeader = view.findViewById<Button>(R.id.btnHeader)
        val btnQuery = view.findViewById<Button>(R.id.btnQuery)
        val methodSpinner = view.findViewById<Spinner>(R.id.methodSpinner)

        var tail = ""
        val methods  = listOf("GET", "POST", "PUT", "DELETE")
        //textView.text = "Category :  " + requireArguments().getInt("position")
        val adapter: SpinnerAdapter =
            ArrayAdapter<Any?>(this@TabFragment.requireContext(), android.R.layout.simple_spinner_dropdown_item, methods)
        methodSpinner.adapter = adapter

        inputUrl.doOnTextChanged { text, start, before, count ->  txtUrl.text = text.toString()+tail}
        btnHeader.setOnClickListener {
            val url : String = txtUrl.text.toString() + "/" + inputHeader.editableText
            txtUrl.text = url
            tail+= "/" + inputHeader.editableText
            inputHeader.setText("")
        }
        btnQuery.setOnClickListener {
            if ("?" in txtUrl.text) {
                val url: String = txtUrl.text.toString() + "&" + inputQuery.editableText + "=" + inputQueryValue.editableText
                txtUrl.text = url
                tail += "&"+ inputQuery.editableText.toString() + "=" + inputQueryValue.editableText
            }
            else {
                val url: String = txtUrl.text.toString() + "?" + inputQuery.editableText + "=" + inputQueryValue.editableText
                txtUrl.text = url
                tail += "?" + inputQuery.editableText.toString() + "=" + inputQueryValue.editableText
            }
            inputQuery.setText("")
            inputQueryValue.setText("")
        }
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