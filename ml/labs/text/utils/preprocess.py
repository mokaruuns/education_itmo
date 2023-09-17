import re
import string
from collections import Counter
from typing import List


def load(filepath: str, with_comma=False) -> List[str]:
    with open(filepath) as file:
        data = file.read()

    ending = ["\\?+", "\\!+", "\\.+", "(\\?|\\!)+", "\\:+", "\\;+"]
    data = re.sub("|".join(ending), "\n", data)
    data = re.sub(r'\d+', '', data)
    data = re.sub(r'"([^"]+)"', r'\n\1\n', data)
    data = re.sub(r'\(([^)]+)\)', r'\n\1\n', data)
    data = re.sub(r'\[([^]]+)\]', r'\n\1\n', data)
    data = re.sub('[^а-яА-Я\n ]', ' ', data)
    data = re.sub(r'( )+', ' ', data)

    if not with_comma:
        data = re.sub(r',', '', data)

    lines = data.split('\n')
    lines = map(str.strip, lines)

    lines = filter(lambda line: len(line) > 5 and 'Chapter' not in line, lines)

    lines = map(lambda s: s.lower(), lines)

    lines = map(lambda s: s + ".", lines)

    lines = set(lines)

    lines = list(lines)

    return lines


def alphabet(lines):
    text = " ".join(lines)
    chars = sorted(set(text))

    char2index = {c: i for i, c in enumerate(chars)}
    index2char = {i: c for i, c in enumerate(chars)}
    return char2index, index2char


if __name__ == '__main__':
    data = load('../texts/voina_i_mir.txt')
    print(data)
    print(alphabet(data))
