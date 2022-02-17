package app.takahashi.yonpachi.countchallengeapp02

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.takahashi.yonpachi.countchallengeapp02.databinding.FragmentThirdBinding
import java.lang.ClassCastException

class ThirdFragment : Fragment() {

    interface  ThirdListener {
        fun onButtonClicked()
    }

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private var listener: ThirdListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.finishButton.setOnClickListener {
            onButtonClicked(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ThirdListener
        if(listener == null) {
            throw ClassCastException("$context must implement ThirdListener")
        }
    }

    fun onButtonClicked(view: View) {
        listener?.onButtonClicked()
    }

}