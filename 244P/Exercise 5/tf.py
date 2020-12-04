import collections
import os
import re
import threading
import time
from concurrent.futures import ThreadPoolExecutor, wait, ALL_COMPLETED


def count(file, counts):
    print("Counting:", file)
    print(threading.current_thread().getName())
    words = re.findall('\w{3,}', open(file).read().lower())
    counts += collections.Counter(w for w in words if w not in stopwords)
    return counts


if __name__ == '__main__':
    stopwords = set(open('stop_words').read().split(','))
    currentDir = os.listdir(".")
    fileList = []
    for file in currentDir:
        if file.endswith(".txt"):
            fileList.append(file)
    print(fileList)

    counts = collections.Counter()
    pool = ThreadPoolExecutor(max_workers=len(fileList))
    start = time.time()
    tasks = [pool.submit(count, file, counts) for file in fileList]
    pool.shutdown()
    wait(tasks, return_when=ALL_COMPLETED)
    elapsed = round((time.time() - start), 4)

    print("---------- Word counts (top 40) -----------")
    for (w, c) in counts.most_common(40):
        print(w, '-', c)
    print("Elapsed time:", elapsed, "s")
