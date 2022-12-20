from os import listdir
from os.path import isfile, join

import pandas as pd

dir = "messages/"
files = [f for f in listdir(dir) if isfile(join(dir, f))]

dataframe = pd.DataFrame(columns=['subject', 'message', 'spam'])

dict_df = {'subject': [], 'message': [], 'spam': []}
for file in files:
    with open(dir + file, "r") as f:
        subject = f.readline().strip().split(":")[1].strip()
        body = f.read().strip()
        if subject == "":
            continue
        spam = 1 if "spmsg" in file else 0
        dict_df['subject'].append(subject)
        dict_df['message'].append(body)
        dict_df['spam'].append(spam)

dataframe = pd.DataFrame(dict_df)
dataframe.to_csv("messages.csv", index=False)
