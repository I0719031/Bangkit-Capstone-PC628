from flask import Flask, request, jsonify
from recommendation_engine import rekomendasi_resep #Importing engine function
import pandas as pd
import pickle

app = Flask(__name__)

app.config['JSON_SORT_KEYS'] = False

dataset_resep = pd.read_csv('dataset_all.csv')

cosine_sim_df = pd.read_csv('cosine_sim_df.csv')


#API endpoint
@app.route('/api', methods=['POST'])
def process_request():


    #Parse received JSON request
    user_input = request.get_json()

    #Extract show title
    Bahan = user_input['Ingredient']

    #Call recommendation engine
    rekomendasi_resep_dict = rekomendasi_resep(Bahan, cosine_sim_df, dataset_resep)

    return jsonify(rekomendasi_resep_dict)

@app.route("/")
def hello_world():
    return "<p>Welcome to Leckers!</p>"