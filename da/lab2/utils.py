import matplotlib.pyplot as plt
import statsmodels.api as sm


def stationarity_test(df_row):
    '''

    :param df_row: array_like, 1d

    :return: True if series is stationarity else False
    '''
    test = sm.tsa.adfuller(df_row)
    return test[0] <= test[4]['5%']
