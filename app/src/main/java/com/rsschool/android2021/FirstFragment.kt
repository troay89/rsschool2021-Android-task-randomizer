package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    interface CallBacks {
        fun onGeneration(min: Int, max: Int)
    }

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var minValue: EditText
    private lateinit var maxValue: EditText
    private var callBack: CallBacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = context as CallBacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {

            val min = minValue.text.toString()
            val max = maxValue.text.toString()

            if(min.isBlank() || max.isBlank()) Toast.makeText(context, "заполните все поля!", Toast.LENGTH_LONG).show()
            else if(min.toIntOrNull() == null || max.toIntOrNull() == null) Toast.makeText(context, "Программа работает с числами не более 2147483647", Toast.LENGTH_LONG).show()
//            else if(min.toInt() < 0 || max.toInt() < 0) Toast.makeText(context, "Число должно быть положительное", Toast.LENGTH_LONG).show()
            else if(min.toInt() > max.toInt()) Toast.makeText(context, "Максисмпльное число должно быть больше минимального числа", Toast.LENGTH_LONG).show()
            else callBack?.onGeneration(min.toInt(), max.toInt())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}