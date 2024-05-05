# Devices Price Classification System

## Overview

This project implements a Devices Price Classification System consisting of two main components:

1. Python project: Predicts device prices based on their specifications.
2. SpringBoot project: Provides RESTful API endpoints to interact with the Python project and store predicted prices in a database.

## Project Structure

The project is organized into two main directories:

- **Python**: Contains the Python implementation.
- **SpringBoot**: Contains the Spring Boot implementation.

## Python Project

### Files
- **app.py**: Main Python application file.
- **EDA.ipynb**: Jupyter notebook for exploratory data analysis (EDA).
- **processed_test_data.csv**: Processed test dataset.
- **processed_train_data.csv**: Processed train dataset.
- **templates/index.html**: HTML template for visualization.
- **test-test.csv**: Test dataset in CSV format.
- **test.xlsx**: Test dataset in Excel format.
- **train-train.csv**: Train dataset in CSV format.
- **train.xlsx**: Train dataset in Excel format.
- **xgb_model.pkl**: Pickled XGBoost model.
- **XGBoost.ipynb**: Jupyter notebook for XGBoost model training.

### Usage
1. Ensure Python and required libraries are installed.
2. Run `app.py` to start the application.
3. Access the API endpoints for predictions.

### Retraining the Model
- You can retrain the model by running the `XGBoost.ipynb` Jupyter notebook.
- The model was initially trained using the following hyperparameters: 
  - Best Parameters:
    - `gamma`: 0.2
    - `importance_type`: 'gain'
    - `learning_rate`: 0.1
    - `max_delta_step`: 1
    - `max_depth`: 3
    - `min_child_weight`: 3
    - `n_estimators`: 300
    - `reg_lambda`: 0.1

### Exploratory Data Analysis
- Explore the dataset and insights using the `EDA.ipynb` notebook.

## SpringBoot Project

### Files
- **src/main/java/com/example/Devices/**: Java source files.
- **src/main/resources/**: Resource files including application properties and static assets.
- **src/test/java/com/example/Devices/**: Test files.

### Usage
1. Navigate to the SpringBoot directory.
2. Build the project: `mvn clean install`.
3. Run it using: `mvn spring-boot:run`.

### Endpoints
- `POST /api/devices/`: Retrieve a list of all devices.
- `GET /api/devices/{id}`: Retrieve details of a specific device by ID.
- `POST /api/devices/`: Add a new device.
- `POST /api/predict/{deviceId}`: Predict the price for a device.

## Running the Applications

- Ensure you have suitable dependencies installed.
- Start the Python application by running `app.py`.
- Start the SpringBoot application (as mentioned in the usage section above) and access it through: [http://localhost:5000/](http://localhost:5000/)

## Using the Web App

Dataset columns are as follows:
- Battery Power: Total energy a battery can store in one time measured in mAh
- Has Bluetooth: Has Bluetooth or not
- Clock Speed: The speed at which the microprocessor executes instructions
- Dual Sim: Has dual sim support or not
- Front Camera MP: Front Camera megapixels
- Has 2G/3G/4G: Has 4G, 3G, or 2G (write 4 or 3 or 2)
- Internal Memory: Internal Memory in Gigabytes
- Mobile Depth: Mobile Depth in cm
- Mobile Weight: Weight of mobile phone
- Number of Cores: Number of cores of the processor
- Primary Camera MP: Primary Camera megapixels
- Pixel Density: Pixel Resolution ('px_height' * 'px_width' / 'screen_size').
- RAM: Random Access Memory in Megabytes
- diagonalScreenSize: sqrt('sc_h'**2 + 'sc_w'**2)
- Talk Time: longest time that a single battery charge will last when you are
- Has Touch Screen: Has touch screen or not
- Has WiFi: Has wifi or not

Output:
- Price Range: This is the target variable with the value of:
  - 0 (low cost)
  - 1 (medium cost)
  - 2 (high cost)
  - 3 (very high cost)

## Evaluation Metrics

- Accuracy on Validation Set: 91.5%
- Accuracy on Test Set: 90.5%

Classification Report:
              precision    recall  f1-score   support

           0       0.96      0.96      0.96        46
           1       0.85      0.92      0.88        49
           2       0.92      0.80      0.85        55
           3       0.91      0.96      0.93        50

     accuracy                           0.91       200
     macro avg       0.91      0.91      0.91       200
     weighted avg       0.91      0.91      0.90       200
