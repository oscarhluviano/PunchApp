package com.example.punchapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.punchapp.model.Product


class ProductFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun miClick(product: Product) {
        Toast.makeText(requireContext(), "Juego: ${product.title}", Toast.LENGTH_SHORT).show()

        val bundle = Bundle()
        bundle.putString("dato1", "${product.title}")
        parentFragmentManager.setFragmentResult("datos",bundle)
    }
}