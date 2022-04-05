package com.example.punchapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.punchapp.adapter.ProductAdapter
import com.example.punchapp.adapter.PurchaseHistoryAdapter
import com.example.punchapp.databinding.FragmentPurchaseHistoryBinding
import com.example.punchapp.model.Product


class PurchaseHistoryFragment : Fragment(), PurchaseHistoryAdapter.OnItemListener {

    private lateinit var binding: FragmentPurchaseHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPurchaseHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datos = ArrayList<Product>()

        for(i in 1 until 20){
            val gameTmp = Product(i, "Título $i", "Costo $i")
            datos.add(gameTmp)
        }

        val adapter = PurchaseHistoryAdapter(requireContext(), datos, this)

        with(binding){
            //Recyclerview requiere un LayoutManager
            rvHistory.layoutManager = LinearLayoutManager(requireContext())
            rvHistory.adapter = adapter
        }
    }

    override fun miClick(product: Product) {
        Toast.makeText(requireContext(), "Juego: ${product.title}", Toast.LENGTH_SHORT).show()

        val bundle = Bundle()
        bundle.putString("dato1", "${product.title}")
        parentFragmentManager.setFragmentResult("datos",bundle)
    }

}