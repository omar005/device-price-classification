from flask import Flask, request, jsonify, render_template
import pickle
import pandas as pd

app = Flask(__name__)

# Load the trained XGBoost model
with open('xgb_model.pkl', 'rb') as f:
    xgb_model = pickle.load(f)

# Define a function to preprocess input data
def preprocess_input(data):
    # Convert the input data into a DataFrame
    data_df = pd.DataFrame(data, index=[0])
    # Rename the columns to match the feature names used during model training
    data_df.columns = ['battery_power', 'blue', 'clock_speed', 'dual_sim', 'fc', 'int_memory', 
                       'm_dep', 'mobile_wt', 'n_cores', 'pc', 'ram', 'talk_time', 'touch_screen', 
                       'wifi', 'combined_g', 'screen_size', 'pixel_density']
    return data_df

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/predict', methods=['POST'])
def predict():
    # Receive JSON data from the request
    device_data = request.json
    #print("Received Data:", device_data) #uncomment to print Received Data

    # Check if data is empty
    if not device_data:
        return "Error: No data provided", 400

    try:
        # Preprocess input data
        processed_data = preprocess_input(device_data)

        # Predict using the trained model
        predicted_price = xgb_model.predict(processed_data)[0] # Get the first prediction as a single value
        
        # Convert prediction to a list
        predicted_price = predicted_price.tolist()

        #print(predicted_price) #uncomment to print predicted price
        # Return the predicted price as a JSON response
        return jsonify({"predicted_price": predicted_price})

    except KeyError as e:
        print("Error: Missing field in request data:", e)
        return "Error: Missing field in request data", 400

    except ValueError as e:
        print("Error: Invalid value in request data:", e)
        return "Error: Invalid value in request data", 400


if __name__ == '__main__':
    app.run(debug=True)

