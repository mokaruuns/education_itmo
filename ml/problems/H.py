import numpy as np

# Задаем данные
x = np.array([1, 2, 2, 1])  # категориальный признак X
y = np.array([1, 2, 3, 4])  # числовой признак Y

# Вычисляем условную дисперсию D(Y|X)
y_given_x = y - np.mean(y)  # вычитаем среднее значение Y для X=3
d_y_given_x = np.var(y_given_x, ddof=1)

print("Условная дисперсия D(Y|X):", d_y_given_x)
