package com.example.punchapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.punchapp.adapter.ProductAdapter
import com.example.punchapp.databinding.FragmentProductBinding
import com.example.punchapp.databinding.ProductElementBinding
import com.example.punchapp.model.Product


class ProductFragment : Fragment(), ProductAdapter.OnItemListener {

    private lateinit var binding: FragmentProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datos = ArrayList<Product>()

        for(i in 1 until 20){
            val productTmp = Product(i, "Título $i", "Costo $i")
            datos.add(productTmp)
        }

        val adapter = ProductAdapter(requireContext(), datos, this)

        with(binding){
            //Recyclerview requiere un LayoutManager
            rvProducts.layoutManager = LinearLayoutManager(requireContext())
            rvProducts.adapter = adapter
        }
    }

    override fun miClick(product: Product) {
        Toast.makeText(requireContext(), "Juego: ${product.title}", Toast.LENGTH_SHORT).show()

        val bundle = Bundle()
        bundle.putString("dato1", "${product.title}")
        parentFragmentManager.setFragmentResult("datos",bundle)
    }

}