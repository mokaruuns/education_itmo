from collections import defaultdict
import random
from copy import copy
from typing import List

from utils.preprocess import load

SPACE = ' '
END = '.'


class MarkovChain:
    def __init__(self, seqlen=5):
        random.seed(42)
        self.words = None
        self.graph = None
        self.seqlen = seqlen

    def train(self, text: List[str]) -> None:
        self.graph = {}
        self.words = defaultdict(float)
        words_count = 0

        # add token after seq
        for line in text:
            for i in range(len(line) - self.seqlen):
                v = line[i: i + self.seqlen]
                self.graph.setdefault(v, defaultdict(float))
                self.graph[v][line[i + self.seqlen]] += 1

        # normalize
        for seq in self.graph:
            cnt = sum(self.graph[seq].values())
            for char in self.graph[seq]:
                self.graph[seq][char] /= cnt

        # count words
        for line in text:
            for word in line.split():
                word = word.rstrip(END)
                self.words[word] += 1
                words_count += 1

        #  normalize
        for word in self.words:
            self.words[word] /= words_count

    def _predict(self, seq):
        if seq not in self.graph:
            return None
        probabilities = list(self.graph.get(seq, {}).values())
        if not probabilities:
            return None
        next_char = random.choices(list(self.graph[seq].keys()), weights=probabilities)[0]
        return next_char

    def inference(self, prefix="", max_length=60):
        assert self.seqlen <= max_length

        result = copy(prefix)

        while len(result) < self.seqlen:
            probabilities = list(self.words.values())
            if not probabilities:
                break
            next_word = random.choices(list(self.words.keys()), weights=probabilities)[0]
            result += next_word + SPACE

        while len(result) < max_length:
            suffix = result[-self.seqlen:]
            pred = self._predict(suffix)

            if pred is not None:
                result += pred
            else:
                break

        if len(result) >= max_length:
            if result[-1] == SPACE:
                result = result.rstrip()
            elif result[-1] != END:
                result = result[:result.rfind(SPACE)]

        return result


if __name__ == '__main__':
    lines = load('../texts/voina_i_mir.txt')
    chain = MarkovChain(seqlen=5)
    chain.train(lines)
    local_prefix = "груша "
    evals = [chain.inference(prefix=local_prefix) for _ in range(20)]
    print(evals)
