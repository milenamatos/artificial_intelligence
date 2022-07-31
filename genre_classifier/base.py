import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler

def read_csv():
    path = 'data/songs_normalize.csv'
    dataset = pd.read_csv(path)
    
    dataset = dataset.drop(['artist', 'song', 'year', 'duration_ms', 'popularity'],axis=1)
    dataset = dataset.query("genre != 'set()'")

    # genre_count = dataset.groupby("genre").size().sort_values(ascending=False) .reset_index(name='count')
    # print(genre_count)

    dataset['genre'] = dataset['genre'].str.split(',').str[0]
   
    return dataset

def get_X_y_data():
    dataset = read_csv()

    Xdata = dataset.drop(['genre'],axis=1)
    Ydata = dataset['genre']

    return Xdata, Ydata

def get_normalized_data():
    Xdata, Ydata = get_X_y_data()

    ms = MinMaxScaler()
    Xdata = ms.fit_transform(Xdata)

    return train_test_split(Xdata[1:], Ydata[1:], test_size=0.3)