import seaborn as sns, matplotlib.pyplot as plt
from sklearn.metrics import classification_report, confusion_matrix

def print_report(classifier, X_test, y_test, y_predict):
    cm = confusion_matrix(y_test, y_predict)
    sns.heatmap(cm, annot=True)

    print(classification_report(y_test, y_predict))

    TestScore = classifier.score(X_test, y_test)
    print("Acurácia de " + str(TestScore*100) + "%")

    fig=plt.gcf()
    fig.set_size_inches(15,8)
    plt.show()