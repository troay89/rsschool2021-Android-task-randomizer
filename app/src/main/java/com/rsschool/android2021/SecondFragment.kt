package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    interface CallBacks {
        fun getGeneration(random: Int)
    }

    private var backButton: Button? = null
    private var result: TextView? = null
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
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0


        val randomNumber = generate(min, max)
        result?.text = randomNumber.toString()

        //хотя бы почитайте https://developer.android.com/guide/navigation/navigation-custom-back#implement_custom_back_navigation

        fun back() {
            callBack?.getGeneration(randomNumber)
        }

        backButton?.setOnClickListener {
            back()
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            back()
        }
    }

    private fun generate(min: Int, max: Int) = (min..max).random()

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle().apply {
                putInt(MIN_VALUE_KEY, min)
                putInt(MAX_VALUE_KEY, max)
            }

            fragment.arguments = args
            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}