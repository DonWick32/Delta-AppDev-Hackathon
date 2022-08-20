package com.example.megaapi


import android.R.attr
import android.content.Context;
import android.os.Bundle;
import android.util.Log
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*

import androidx.annotation.Nullable;
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment;
import java.io.IOException
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.text.method.ScrollingMovementMethod
import okhttp3.*
import android.content.ClipboardManager
import org.json.JSONObject
import android.R.attr.label

import android.content.ClipData
import android.content.Context.CLIPBOARD_SERVICE
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService





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
        val txtResponse = view.findViewById<TextView>(R.id.txtResponse)
        val txt = view.findViewById<TextView>(R.id.txt)
        val txt1 = view.findViewById<TextView>(R.id.textView3)
        val txt2 = view.findViewById<TextView>(R.id.textView5)
        val txt3 = view.findViewById<TextView>(R.id.textView6)
        val txt4 = view.findViewById<TextView>(R.id.textView7)
        val inputHeader = view.findViewById<EditText>(R.id.inputHeader)
        val inputQuery = view.findViewById<EditText>(R.id.inputQuery)
        val inputQueryValue = view.findViewById<EditText>(R.id.inputQueryValue)
        val inputData = view.findViewById<EditText>(R.id.inputData)
        val btnHeader = view.findViewById<Button>(R.id.btnHeader)
        val btnQuery = view.findViewById<Button>(R.id.btnQuery)
        val btnRequest = view.findViewById<Button>(R.id.btnRequest)
        val btnBack = view.findViewById<Button>(R.id.btnBack)
        val btnCopy = view.findViewById<Button>(R.id.btnCopy)
        val btnExport = view.findViewById<Button>(R.id.btnExport)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        val methodSpinner = view.findViewById<Spinner>(R.id.methodSpinner)
        txtResponse.movementMethod = ScrollingMovementMethod()
        txtResponse.visibility = View.GONE
        txt.visibility = View.GONE
        btnBack.visibility = View.GONE
        btnCopy.visibility = View.GONE
        btnExport.visibility = View.GONE
        inputData.visibility = View.GONE
        btnSubmit.visibility = View.GONE

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

        btnRequest.setOnClickListener {
            Log.d("TAG",methodSpinner.selectedItem.toString())
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            var client = OkHttpClient()

            if (methodSpinner.selectedItem.toString() == "GET") {
                try {
                    var request = Request.Builder().url(txtUrl.text.toString()).build()
                    var response = client.newCall(request).execute()
                    var jsonObject = JSONObject(response.body()!!.string())
                    txtResponse.text = jsonObject.toString(4)

                    inputUrl.visibility = View.GONE
                    txtUrl.visibility = View.GONE
                    inputHeader.visibility = View.GONE
                    inputQuery.visibility = View.GONE
                    inputQueryValue.visibility = View.GONE
                    btnHeader.visibility = View.GONE
                    btnQuery.visibility = View.GONE
                    btnRequest.visibility = View.GONE
                    methodSpinner.visibility = View.GONE
                    txt1.visibility = View.GONE
                    txt2.visibility = View.GONE
                    txt3.visibility = View.GONE
                    txt4.visibility = View.GONE

                    txtResponse.visibility = View.VISIBLE
                    txt.visibility = View.VISIBLE
                    btnBack.visibility = View.VISIBLE
                    btnCopy.visibility = View.VISIBLE
                    btnExport.visibility = View.VISIBLE


                } catch (e: IOException) {
                    Log.d("TAG", "Request failed.")
                }
            }

            else if (methodSpinner.selectedItem.toString() == "POST"){
                inputUrl.visibility = View.GONE
                txtUrl.visibility = View.GONE
                inputHeader.visibility = View.GONE
                inputQuery.visibility = View.GONE
                inputQueryValue.visibility = View.GONE
                btnHeader.visibility = View.GONE
                btnQuery.visibility = View.GONE
                btnRequest.visibility = View.GONE
                methodSpinner.visibility = View.GONE
                txt1.visibility = View.GONE
                txt2.visibility = View.GONE
                txt3.visibility = View.GONE
                txt4.visibility = View.GONE

                inputData.visibility = View.VISIBLE
                btnSubmit.visibility = View.VISIBLE

                btnSubmit.setOnClickListener {
                    val JSON = MediaType.parse("application/json;charset=utf-8")
                    var request = Request.Builder().method("POST", RequestBody.create(JSON,inputData.text.toString())).url(txtUrl.text.toString()).build()
                    //Log.d("Random",RequestBody.create(JSON,inputData.text.toString()))

                    try {
                        var response = client.newCall(request).execute()
                        var jsonObject = JSONObject(response.body()!!.string())
                        txtResponse.text = jsonObject.toString(4)
                        inputData.visibility = View.GONE
                        btnSubmit.visibility = View.GONE

                        txtResponse.visibility = View.VISIBLE
                        txt.visibility = View.VISIBLE
                        btnBack.visibility = View.VISIBLE
                        btnCopy.visibility = View.VISIBLE
                        btnExport.visibility = View.VISIBLE

                    } catch (e: IOException) {
                        Log.d("TAG", "Request failed.")
                    }
                }

            }

        }

        btnBack.setOnClickListener {
            inputUrl.visibility = View.VISIBLE
            txtUrl.visibility = View.VISIBLE
            inputHeader.visibility = View.VISIBLE
            inputQuery.visibility = View.VISIBLE
            inputQueryValue.visibility = View.VISIBLE
            btnHeader.visibility = View.VISIBLE
            btnQuery.visibility = View.VISIBLE
            btnRequest.visibility = View.VISIBLE
            methodSpinner.visibility = View.VISIBLE
            txt1.visibility = View.VISIBLE
            txt2.visibility = View.VISIBLE
            txt3.visibility = View.VISIBLE
            txt4.visibility = View.VISIBLE

            txtResponse.visibility = View.GONE
            txt.visibility = View.GONE
            btnBack.visibility = View.GONE
            btnCopy.visibility = View.GONE
            btnExport.visibility = View.GONE
        }

        btnCopy.setOnClickListener {
            val clipboard = requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText(label.toString(), txtResponse.text)
            clipboard!!.setPrimaryClip(clip)
            Toast.makeText(activity,"Copied to Clipboard!",Toast.LENGTH_SHORT).show()
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