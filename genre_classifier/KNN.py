from sklearn.neighbors import KNeighborsClassifier
from base import get_normalized_data
from report import print_report

X_train, X_test, y_train, y_test = get_normalized_data()

knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, y_train)

y_predict_knn = knn.predict(X_test)
print_report(knn, X_test, y_test, y_predict_knn)

