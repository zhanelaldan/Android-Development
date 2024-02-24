package com.example.androiddodo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddodo.databinding.NewItemBinding
import com.example.androiddodo.databinding.PizzaItemBinding
import com.example.androiddodo.model.Pizza
import java.util.Random

class PizzaAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val pizzaList: ArrayList<Pizza> = ArrayList()
    private val TYPE_NORMAL_PIZZA = 1
    private val TYPE_NEW_PIZZA = 2

    fun setData(pizzas: ArrayList<Pizza>){
        pizzaList.clear()
        pizzaList.addAll(pizzas)

        /**
         * for list refreshing
         */
        notifyDataSetChanged()
    }


    /**
     * view for every object
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_NORMAL_PIZZA -> {
                val binding = PizzaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NormalPizzaViewHolder(binding)
            }
            TYPE_NEW_PIZZA -> {
                val binding = NewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NewPizzaViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }





        //        return ViewHolder(
//            ItemPizzaBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        )
    }

    override fun getItemCount() = pizzaList.size

    /**
     * call method from ViewHolder
     */

    override fun getItemViewType(position: Int): Int {
        /**
         * По определенным булевым значениям которые уже установлены
         */
//        return if (pizzaList[position].isNew) {
//            TYPE_NEW_PIZZA
//        } else {
//            TYPE_NORMAL_PIZZA
//        }
        val random = Random()
        return if (random.nextBoolean()) {
            TYPE_NEW_PIZZA
        } else {
            TYPE_NORMAL_PIZZA
        }
    }

    /**
     * call method from ViewHolder
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pizza = pizzaList[position]
        when (holder) {
            is NormalPizzaViewHolder -> holder.bindNormalPizza(pizza)
            is NewPizzaViewHolder -> holder.bindNewPizza(pizza)
        }
    }

    private var onItemClickListener: ((Pizza) -> Unit)? = null

    fun setOnItemClickListener(listener: (Pizza) -> Unit){
        onItemClickListener = listener
    }


    inner class NormalPizzaViewHolder(private val binding: PizzaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener{
                val position = adapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    val pizza = pizzaList[position]
                    onItemClickListener?.invoke(pizza)
                }
            }
        }

        fun bindNormalPizza(pizza: Pizza) {
            with(binding){
                imageView.setImageResource(pizza.imageRes)
                pizzaName.text= pizza.name
                pizzaDescription.text = pizza.description
                priceButton.text = "${pizza.price} KZT"
            }
        }
    }

    inner class NewPizzaViewHolder(private val binding: NewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener{
                val position = adapterPosition
                if(position!=RecyclerView.NO_POSITION){
                    val pizza = pizzaList[position]
                    onItemClickListener?.invoke(pizza)
                }
            }
        }

        fun bindNewPizza(pizza: Pizza) {
            with(binding){
                imageView.setImageResource(pizza.imageRes)
                newName.text= pizza.name
                newDesc.text = pizza.description
                priceButton.text = "${pizza.price} KZT"
            }
        }
    }


}