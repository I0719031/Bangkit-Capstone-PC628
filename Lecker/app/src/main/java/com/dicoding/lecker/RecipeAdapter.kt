package com.dicoding.lecker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.lecker.databinding.ItemRecipeBinding

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    private var recipeList: List<Recipe> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    fun updateData(recipes: List<Recipe>) {
        recipeList = recipes
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.tvTitle.text = recipe.title
            binding.tvIngredients.text = recipe.ingredients
            binding.tvSteps.text = recipe.steps
        }
    }
}
