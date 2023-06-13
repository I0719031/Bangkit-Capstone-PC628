import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
 

dataset_resep = pd.read_csv('dataset_all.csv')
cosine_sim_df = pd.read_csv('cosine_sim_df.csv')


tf = TfidfVectorizer()
tf.fit(dataset_resep['Parser Ingredient'])

def rekomendasi_resep(bahan, similarity_data=cosine_sim_df, items=dataset_resep[['Title', 'Ingredients','Steps', 'Parser Ingredient']], k=10):
    # Membuat fungsi untuk mencari kesamaan bahan menggunakan metode cosine similarity
    def get_similarity(query, ingredients):
        query_vector = tf.transform([query])
        ingredient_vectors = tf.transform(ingredients)
        similarities = cosine_similarity(query_vector, ingredient_vectors)
        return similarities
    
    # Menghitung kesamaan bahan dengan menggunakan fungsi get_similarity
    similarities = get_similarity(bahan, items['Parser Ingredient'])
    
    # Mengambil indeks resep dengan kesamaan tertinggi
    closest_indices = similarities.argsort()[0][-k:][::-1]
    
    # Mengambil data resep berdasarkan indeks terdekat
    recommendations = items.iloc[closest_indices]
    
    # Filter rekomendasi berdasarkan bahan utama
    # filtered_recommendations = recommendations[recommendations['Parser Ingredient'].apply(lambda x: any(ingr in x for ingr in bahan_utama))]
    
    # Memilih kolom yang diinginkan pada output
    filtered_recommendations = recommendations.loc[:, ['Title', 'Ingredients', 'Steps']]
    
    # Mengubah output menjadi format yang diinginkan
    result = [{'title': row['Title'], 'ingredients': row['Ingredients'], 'steps': row['Steps']} for idx, row in filtered_recommendations.iterrows()]
    response = {'result': result}
    
    return response