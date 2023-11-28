import pandas as pd
import seaborn as sns
iris = pd.read_csv('archive (1)/Iris.csv', names=['sepal_length', 'sepal_width', 'petal_length', 'petal_width', 'class'])
print(iris.head())