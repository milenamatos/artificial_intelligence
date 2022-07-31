from sklearn.naive_bayes import MultinomialNB
from base import get_normalized_data
from report import print_report

X_train, X_test, y_train, y_test = get_normalized_data()

nb = MultinomialNB()
nb.fit(X_train, y_train)

y_predict_nb = nb.predict(X_test)
print_report(nb, X_test, y_test, y_predict_nb)
