from sklearn.svm import SVC
from base import get_normalized_data
from report import print_report

X_train, X_test, y_train, y_test = get_normalized_data()

svc = SVC(kernel="linear")
svc.fit(X_train, y_train)

y_predict_SVC = svc.predict(X_test)
print_report(svc, X_test, y_test, y_predict_SVC)
