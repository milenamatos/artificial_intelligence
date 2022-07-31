import seaborn as sns, matplotlib.pyplot as plt
from base import read_csv

dataset = read_csv()

print(dataset.head())

genre_count = dataset.groupby("genre").size().sort_values(ascending=False) .reset_index(name='count')
print(genre_count)

sns.heatmap(data=dataset.corr(), annot=True)

# sns.scatterplot(x='energy', y='loudness', hue='genre', data=dataset)

fig=plt.gcf()
fig.set_size_inches(20,6)
plt.show()