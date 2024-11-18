Catch Cheaters
Part 1
You are running a classroom and suspect that some of your students are passing around the answers to multiple choice questions disguised as random strings. Your task is to write a function that, given a list of words and a string, finds and returns the word in the list that is scrambled up inside the string, if any exists. There will be at most one matching word. The letters don't need to be in order or next to each other. The letters cannot be reused.

Test  Case:

containsWord(Arrays.asList("cat", "baby", "dog", "bird", "car", "ax"), "tcabnihjs");
containsWord(Arrays.asList("cat", "baby", "dog", "bird", "car", "ax"), "tbcanihjs");
containsWord(Arrays.asList("cat", "baby", "dog", "bird", "car", "ax"), "baykkjl");
containsWord(Arrays.asList("cat", "baby", "dog", "bird", "car", "ax"), "bbabylkkj");
containsWord(Arrays.asList("cat", "baby", "dog", "bird", "car", "ax"), "ccc");
containsWord(Arrays.asList("cat", "baby", "dog", "bird", "car", "ax"), "breadmaking");
Part 2
After catching your classroom students cheating before, you realize your students are getting craftier and hiding words in 2D grids of letters. The word may start anywhere in the grid, and consecutive letters can be either immediately below or immediately to the right of the previous letter.

Given a grid and a word, write a function that returns the location of the word in the grid as a list of coordinates. If there are multiple matches, return any one.

grid1 = [
['c', 'c', 'x', 't', 'i', 'b'],
['c', 'c', 'a', 't', 'n', 'i'],
['a', 'c', 'n', 'n', 't', 't'],
['t', 'c', 's', 'i', 'p', 't'],
['a', 'o', 'o', 'o', 'a', 'a'],
['o', 'a', 'a', 'a', 'o', 'o'],
['k', 'a', 'i', 'c', 'k', 'i'],
]
word1 = "catnip"
word2 = "cccc"
word3 = "s"
word4 = "bit"
word5 = "aoi"
word6 = "ki"
word7 = "aaa"
word8 = "ooo"
grid2 = [['a']]
word9 = "a"
find_word_location(grid1, word1) => [ (1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4) ]
find_word_location(grid1, word2) =>
[(0, 1), (1, 1), (2, 1), (3, 1)]
OR [(0, 0), (1, 0), (1, 1), (2, 1)]
OR [(0, 0), (0, 1), (1, 1), (2, 1)]
OR [(1, 0), (1, 1), (2, 1), (3, 1)]
find_word_location(grid1, word3) => [(3, 2)]
find_word_location(grid1, word4) => [(0, 5), (1, 5), (2, 5)]
find_word_location(grid1, word5) => [(4, 5), (5, 5), (6, 5)]
find_word_location(grid1, word6) => [(6, 4), (6, 5)]
find_word_location(grid1, word7) => [(5, 1), (5, 2), (5, 3)]
find_word_location(grid1, word8) => [(4, 1), (4, 2), (4, 3)]
find_word_location(grid2, word9) => [(0, 0)]


Part 3
The conflict with your students escalates, and now they are hiding multiple words in a single word grid. Return the location of each word as a list of coordinates. Letters cannot be reused across words.

grid1 = [
['b', 'a', 'b'],
['y', 't', 'a'],
['x', 'x', 't'],
]

words1_1 = ["by","bat"]

find_word_locations(grid1, words1_1) =>
([(0, 0), (1, 0)],
[(0, 2), (1, 2), (2, 2)])

grid2 =[
['A', 'B', 'A', 'B'],
['B', 'A', 'B', 'A'],
['A', 'B', 'Y', 'B'],
['B', 'Y', 'A', 'A'],
['A', 'B', 'B', 'A'],
]
words2_1 = ['ABABY', 'ABY', 'AAA', 'ABAB', 'BABB']

([(0, 0), (1, 0), (2, 0), (2, 1), (3, 1)],
[(1, 1), (1, 2), (2, 2)],
[(3, 2), (3, 3), (4, 3)],
[(0, 2), (0, 3), (1, 3), (2, 3)],
[(3, 0), (4, 0), (4, 1), (4, 2)])

or

([(0, 0), (1, 0), (1, 1), (1, 2), (2, 2)],
[(2, 0), (2, 1), (3, 1)],
[(3, 2), (3, 3), (4, 3)],
[(0, 2), (0, 3), (1, 3), (2, 3)],
[(3, 0), (4, 0), (4, 1), (4, 2)])

or

([(0, 0), (0, 1), (1, 1), (1, 2), (2, 2)],
[(2, 0), (2, 1), (3, 1)],
[(3, 2), (3, 3), (4, 3)],
[(0, 2), (0, 3), (1, 3), (2, 3)],
[(3, 0), (4, 0), (4, 1), (4, 2)])

words2_2 = ['ABABA', 'ABA', 'BAB', 'BABA', 'ABYB']

([(0, 0), (1, 0), (2, 0), (3, 0), (4, 0)],
[(3, 2), (4, 2), (4, 3)],
[(0, 1), (0, 2), (1, 2)],
[(0, 3), (1, 3), (2, 3), (3, 3)],
[(1, 1), (2, 1), (3, 1), (4, 1)])

or

([(0, 0), (1, 0), (2, 0), (3, 0), (4, 0)],
[(3, 2), (4, 2), (4, 3)],
[(0, 1), (0, 2), (0, 3)],
[(1, 2), (1, 3), (2, 3), (3, 3)],
[(1, 1), (2, 1), (3, 1), (4, 1)])


Complexity analysis variables:

r = number of rows
c = number of columns
w = length of the word