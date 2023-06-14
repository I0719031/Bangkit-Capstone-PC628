package com.dicoding.lecker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    private var recipeList: List<Recipe> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
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

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tvTitle)
        private val ingredientsTextView: TextView = itemView.findViewById(R.id.tvIngredients)
        private val stepsTextView: TextView = itemView.findViewById(R.id.tvSteps)

        fun bind(recipe: Recipe) {
            titleTextView.text = recipe.title
            ingredientsTextView.text = recipe.ingredients
            stepsTextView.text = recipe.steps
        }
    }
}
