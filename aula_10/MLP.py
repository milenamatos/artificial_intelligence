import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.neural_network import MLPClassifier

path = 'Iris.csv'
dataset = pd.read_csv(path)

Xdata = dataset[['Id', 'SepalLengthCm', 'SepalWidthCm', 'PetalLengthCm', 'PetalWidthCm', 'Species']]

Ydata = dataset[["Species"]]

X_train, X_test, y_train, y_test = train_test_split(
    Xdata[1:], Ydata[1:], test_size=0.3)

X_train = pd.get_dummies(X_train)
X_test = pd.get_dummies(X_test)

NN = MLPClassifier()
NN.fit(X_train, y_train)
TestScore = NN.score(X_test, y_test)

print("Acurácia de " + str(TestScore*100) + "%")
