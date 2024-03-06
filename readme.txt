README for COMS W3134 Capstone Project: David Kuang dk3260
Dec 18, 2023

1. How long does your program take to produce the answer when using the bst, avl, and hash flags?
bst: 1.773s
avl: 0.937s
hash: 0.538s

2. Copy the results into this readme file.
java AnagramFinder least words.txt bst  1.92s user 0.13s system 106% cpu 1.932 total
java AnagramFinder least words.txt bst  1.82s user 0.10s system 113% cpu 1.698 total
java AnagramFinder least words.txt bst  1.95s user 0.10s system 114% cpu 1.792 total
java AnagramFinder least words.txt bst  1.89s user 0.10s system 114% cpu 1.730 total
java AnagramFinder least words.txt bst  1.86s user 0.10s system 114% cpu 1.714 total
java AnagramFinder least words.txt avl  1.04s user 0.13s system 123% cpu 0.946 total
java AnagramFinder least words.txt avl  1.05s user 0.14s system 115% cpu 1.032 total
java AnagramFinder least words.txt avl  0.96s user 0.10s system 135% cpu 0.781 total
java AnagramFinder least words.txt avl  1.03s user 0.14s system 115% cpu 1.012 total
java AnagramFinder least words.txt avl  1.03s user 0.12s system 125% cpu 0.916 total
java AnagramFinder least words.txt hash  0.58s user 0.12s system 117% cpu 0.596 total
java AnagramFinder least words.txt hash  0.59s user 0.12s system 117% cpu 0.606 total
java AnagramFinder least words.txt hash  0.56s user 0.09s system 154% cpu 0.419 total
java AnagramFinder least words.txt hash  0.60s user 0.11s system 116% cpu 0.613 total
java AnagramFinder least words.txt hash  0.57s user 0.11s system 149% cpu 0.456 total

3. What data structure do you expect to have the best (fastest) performance?
Hash map. Although rehashing is costly, the time complexity for get() to retrieve the MyList of
anagrams is O(1). This beats out the BST and AVL Tree time complexities, O(n) and O(lg n)
respectively.

4. Which one do you expect to be the slowest?
Binary Search Tree. Especially considering that the words in the words.txt file seem to
be lexicographically sorted, this means the tree will be severely lopsided and the get()
method will have O(n) time complexity.

5. Do the results of timing your program’s execution match your expectations?
Yes they do! Hashmap would be the fastest, since getting the MyList of anagrams is simply
indexing an array (O(1)). AVL Tree would be the second fastest, since the maximum depth of
the tree is lg(n) & thus to get the MyList runs in O(lg n) time. BST would be the slowest
due to the tree being very lopsided and the get() method running in linear time. I had thought
that the outcome could be different due to the costliness of rehashing the hash map and
rebalancing the AVL tree, but it seems that the get() method is what influences the execution
time the most since there are so many dictionary entries.

