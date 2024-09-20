
## Coding Interview Patterns

### 1. Pattern: Two Pointers

1. Introduction
2. Pair with Target Sum (easy) - [LeetCode 1](https://leetcode.com/problems/two-sum/)
3. Remove Duplicates (easy) - [LeetCode 83](https://leetcode.com/problems/remove-duplicates-from-sorted-list/) [LeetCode 82](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/) [LeetCode 80](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/) [LeetCode 287](https://leetcode.com/problems/find-the-duplicate-number/) [LeetCode 1089](https://leetcode.com/problems/duplicate-zeros/)
4. Squaring a Sorted Array (easy) - [LeetCode 977](https://leetcode.com/problems/squares-of-a-sorted-array/)
5. Triplet Sum to Zero (medium) - [LeetCode 15](https://leetcode.com/problems/3sum/)
6. Triplet Sum Close to Target (medium) - [LeetCode 16](https://leetcode.com/problems/3sum-closest/)
7. Triplets with Smaller Sum (medium) - [LintCode](https://www.lintcode.com/problem/3sum-smaller/description)
8. Subarrays with Product Less than a Target (medium) - [LeetCode 713](https://leetcode.com/problems/subarray-product-less-than-k/)
9. Dutch National Flag Problem (medium) - [CoderByte](https://coderbyte.com/algorithm/dutch-national-flag-sorting-problem)
10. Problem Challenge 1: Quadruple Sum to Target (medium) - [LeetCode 18](https://leetcode.com/problems/4sum/)
11. Problem Challenge 2: Comparing Strings containing Backspaces (medium) - [LeetCode 844](https://leetcode.com/problems/backspace-string-compare/)
12. Problem Challenge 3: Minimum Window Sort (medium) - [LeetCode 581](https://leetcode.com/problems/shortest-unsorted-continuous-subarray/) [Ideserve](https://www.ideserve.co.in/learn/minimum-length-subarray-sorting-which-results-in-sorted-array)
13. Problem Challenge 4: Maximum Length of Subarray With Positive Product (medium) - [LeetCode 1567](https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/)
14. Problem Challenge 5: Sort the Matrix Diagonally (medium) - [LeetCode 1329](https://leetcode.com/problems/sort-the-matrix-diagonally/)
15. Problem Challenge 6: Sort Characters By Frequency (medium) - [LeetCode 451](https://leetcode.com/problems/sort-characters-by-frequency/)
16. Problem Challenge 7: Sort Array By Parity (medium) - [LeetCode 905](https://leetcode.com/problems/sort-array-by-parity/)
17. Problem Challenge 8: Sort Colors (medium) - [LeetCode 75](https://leetcode.com/problems/sort-colors/)
18. Problem Challenge 9: Wiggle Sort (medium) - [LeetCode 280](https://leetcode.com/problems/wiggle-sort/)


**Sorted Arrays/Lists (Opposite Direction Pointers):**
- [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)
- [344. Reverse String](https://leetcode.com/problems/reverse-string/)
- [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)
- [15. 3Sum](https://leetcode.com/problems/3sum/)
- [16. 3Sum Closest](https://leetcode.com/problems/3sum-closest/)
- [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)
- [80. Remove Duplicates from Sorted Array II](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/)
- [27. Remove Element](https://leetcode.com/problems/remove-element/)

**Linked Lists (Fast & Slow Pointers):**
- [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)
- [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)
- [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)

**Subarrays/Substrings (Sliding Window):**
- [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)
- [713. Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/)
- [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/)
- [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)

**Array Manipulation (In-Place Modification):**
- [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/)

**Additional Challenges:**
- [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)
- [454. 4Sum II](https://leetcode.com/problems/4sum-ii/)
- [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
- [75. Sort Colors](https://leetcode.com/problems/sort-colors/) - Similar to the Dutch National Flag problem.


### 2. Pattern: Fast & Slow pointers

#### Introduction
[emre.me](https://emre.me/coding-patterns/fast-slow-pointers/)
The Fast & Slow pointers pattern, also known as the Tortoise and Hare algorithm, is a two-pointer approach where one pointer moves faster than the other. This pattern is useful for problems involving cyclic lists or sequences.

---

#### LinkedList Cycle Problems

1. [LinkedList Cycle (easy)](https://leetcode.com/problems/linked-list-cycle/)
   Given a linked list, determine if it has a cycle in it.

2. [Linked List Cycle II (medium)](https://leetcode.com/problems/linked-list-cycle-ii/)
   Given a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

3. [Cycle in a Circular Array (hard)](https://leetcode.com/problems/circular-array-loop/)
   You are given a circular array `nums` of positive and negative integers. If a number k at an index is positive, then move forward k steps. Conversely, if it's negative, move backward k steps. Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element. Determine if there is a loop in this array.

---

#### Finding Middle and Palindrome LinkedList Problems

1. [Middle of the LinkedList (easy)](https://leetcode.com/problems/middle-of-the-linked-list/)
   Given a non-empty, singly linked list with head node `head`, return a middle node of the linked list.

2. [Palindrome LinkedList (medium)](https://leetcode.com/problems/palindrome-linked-list/)
   Given the head of a singly linked list, return `true` if it is a palindrome or `false` otherwise.

3. [Rearrange a LinkedList (medium)](https://leetcode.com/problems/reorder-list/)
   You are given the head of a singly linked-list. The list can be represented as: `L0 → L1 → … → Ln - 1 → Ln`. Rearrange the nodes in the list so that the new formed list is: `L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …`.

4. [Odd Even Linked List (medium)](https://leetcode.com/problems/odd-even-linked-list/)
   Given a singly linked list, group all odd nodes together followed by the even nodes. Note that the relative order inside both the even and odd groups should remain as it was in the input.

---

#### Other Fast & Slow Pointer Problems

1. [Happy Number (easy)](https://leetcode.com/problems/happy-number/)
   A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1, or it loops endlessly in a cycle which does not include 1. Determine if a number is happy.

2. [Find the Duplicate Number (medium)](https://leetcode.com/problems/find-the-duplicate-number/)
   Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive. There is only one repeated number in nums, return this repeated number.

3. [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)
4. [Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/)
5. [Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)
6. [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/)
7. [Reorder List](https://leetcode.com/problems/reorder-list/)
8. [Happy Number](https://leetcode.com/problems/happy-number/)
9. [Cycle in a Circular Array](https://leetcode.com/problems/circular-array-loop/)
10. [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/)
11. [Odd Even Linked List](https://leetcode.com/problems/odd-even-linked-list/)
12. [Split Linked List in Parts](https://leetcode.com/problems/split-linked-list-in-parts/)

### 3. Pattern: Sliding Window

#### Introduction
The Sliding Window pattern is a technique for solving problems involving arrays or lists by maintaining a subset of elements within a "window" that moves along the array. This pattern is useful for problems requiring dynamic ranges or intervals within an array.

---

#### Maximum Sum Subarray Problems

1. [Maximum Sum Subarray of Size K (easy)](https://leetcode.com/problems/maximum-average-subarray-i/)
   Given an array of integers and an integer `k`, find the maximum sum of any contiguous subarray of size `k`.

2. [Minimum Size Subarray Sum (medium)](https://leetcode.com/problems/minimum-size-subarray-sum/)
   Given an array of positive integers `nums` and a positive integer `target`, return the minimal length of a contiguous subarray of which the sum is greater than or equal to `target`. If there is no such subarray, return `0` instead.

3. [Maximum Average Subarray I (easy)](https://leetcode.com/problems/maximum-average-subarray-i/)
   Given an array consisting of `n` integers, find the contiguous subarray whose length is equal to `k` that has the maximum average value.

4. [Maximum Sum of Two Non-Overlapping Subarrays (medium)](https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/)
   Given an array `A` of non-negative integers, return the maximum sum of elements in two non-overlapping subarrays, each with length `L` and `M`.

---

#### Longest Substring Problems

1. [Longest Substring with K Distinct Characters (medium)](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/)
   Given a string, find the length of the longest substring in it with no more than `k` distinct characters.

2. [Longest Substring Without Repeating Characters (medium)](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
   Given a string, find the length of the longest substring without repeating characters.

3. [Longest Substring with Same Letters after Replacement (hard)](https://leetcode.com/problems/longest-repeating-character-replacement/)
   Given a string and an integer `k`, find the length of the longest substring that contains the same letters you can get after performing no more than `k` replacements.

4. [Longest Subarray with Ones after Replacement (hard)](https://leetcode.com/problems/max-consecutive-ones-iii/)
   Given a binary array `nums` and an integer `k`, return the maximum number of consecutive `1`'s in the array if you can flip at most `k` `0`'s.

5. [Longest Repeating Character Replacement (medium)](https://leetcode.com/problems/longest-repeating-character-replacement/)
   Given a string `s` that consists of only uppercase English letters, you can perform at most `k` operations to change any character in `s` to any other uppercase English character. Find the length of the longest substring containing the same letter you can get after performing the above operations.

---

#### Problems Involving Subarray/Substring

1. [Smallest Subarray with a given sum (easy)](https://leetcode.com/problems/minimum-size-subarray-sum/)
   Given an array of positive numbers and a positive number `S`, find the length of the smallest contiguous subarray whose sum is greater than or equal to `S`. Return `0` if no such subarray exists.

2. [Fruits into Baskets (medium)](https://leetcode.com/problems/fruit-into-baskets/)
   Given an array `fruits` where `fruits[i]` is the type of fruit the `i-th` tree produces, return the maximum number of fruits you can pick if you are allowed to pick exactly two different types of fruits.

3. [Max Consecutive Ones II (medium)](https://leetcode.com/problems/max-consecutive-ones-ii/)
   Given a binary array `nums`, return the maximum number of consecutive `1`'s in the array if you can flip at most one `0`.

4. [Max Consecutive Ones III (medium)](https://leetcode.com/problems/max-consecutive-ones-iii/)
   Given a binary array `nums` and an integer `k`, return the maximum number of consecutive `1`'s in the array if you can flip at most `k` `0`'s.

5. [Longest Subarray of 1's After Deleting One Element (medium)](https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/)
   Given a binary array `nums`, return the length of the longest subarray containing only `1`s after deleting one element.

6. [Contains Duplicate II (easy)](https://leetcode.com/problems/contains-duplicate-ii/)
   Given an integer array `nums` and an integer `k`, return `true` if there are two distinct indices `i` and `j` in the array such that `nums[i] == nums[j]` and `abs(i - j) <= k`.

---

#### Problem Challenges

1. [Permutation in a String (medium)](https://leetcode.com/problems/permutation-in-string/)
   Given two strings `s1` and `s2`, return `true` if `s2` contains a permutation of `s1`, or `false` otherwise.

2. [String Anagrams (medium)](https://leetcode.com/problems/find-all-anagrams-in-a-string/)
   Given a string `s` and a non-empty string `p`, find all the start indices of `p`'s anagrams in `s`.

3. [Smallest Window containing Substring (hard)](https://leetcode.com/problems/minimum-window-substring/)
   Given two strings `s` and `t` of lengths `m` and `n` respectively, return the minimum window substring of `s` such that every character in `t` (including duplicates) is included in the window. If there is no such substring, return the empty string `""`.

4. [Words Concatenation (hard)](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)
   You are given a string `s` and an array of strings `words` of the same length. Return all starting indices of substring(s) in `s` that is a concatenation of each word in `words` exactly once, in any order, and without any intervening characters.

5. [Minimum Window Subsequence (hard)](https://leetcode.com/problems/minimum-window-subsequence/)
   Given strings `s1` and `s2`, return the minimum window in `s1` which will contain all the characters in `s2` in the same order.

6. [Longest Substring with At Most Two Distinct Characters (medium)](https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/)
   Given a string `s`, find the length of the longest substring that contains at most two distinct characters.

7. [Find All Anagrams in a String (medium)](https://leetcode.com/problems/find-all-anagrams-in-a-string/)
   Given a string `s` and a non-empty string `p`, find all the start indices of `p`'s anagrams in `s`.

8. [Substring with Concatenation of All Words (hard)](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)
   Given a string `s` and an array of strings `words` of the same length, return all starting indices of substring(s) in `s` that is a concatenation of each word in `words` exactly once, in any order, and without any intervening characters.

### 4. Pattern: Merge Intervals

#### Introduction
The Merge Intervals pattern is useful for dealing with problems where a set of intervals is given, and we need to either merge overlapping intervals or figure out some properties of the intervals after merging them. This pattern is often applied to calendar or scheduling problems.

---

#### Basic Merge Interval Problems

1. [Merge Intervals (medium)](https://leetcode.com/problems/merge-intervals/)
   Given a collection of intervals, merge all overlapping intervals.

2. [Insert Interval (medium)](https://leetcode.com/problems/insert-interval/)
   Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

3. [Intervals Intersection (medium)](https://leetcode.com/problems/interval-list-intersections/)
   Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order. Return the intersection of these two interval lists.

4. [Meeting Rooms (easy)](https://leetcode.com/problems/meeting-rooms/)
   Given an array of meeting time intervals consisting of start and end times, determine if a person could attend all meetings.

5. [Meeting Rooms II (medium)](https://leetcode.com/problems/meeting-rooms-ii/)
   Given an array of meeting time intervals consisting of start and end times, find the minimum number of conference rooms required.

---

#### Conflicting Appointments

1. [Conflicting Appointments (medium)](https://www.geeksforgeeks.org/check-if-any-two-intervals-overlap-among-a-given-set-of-intervals/)
   Given an array of time intervals, determine if a person can attend all appointments without any overlaps.

---

#### Advanced Merge Interval Problems

1. [Minimum Meeting Rooms (hard)](https://www.lintcode.com/problem/meeting-rooms-ii/)
   Given a list of intervals representing the start and end time of `N` meetings, find the minimum number of meeting rooms required.

2. [Maximum CPU Load (hard)](https://www.geeksforgeeks.org/maximum-cpu-load-from-the-given-list-of-jobs/)
   Given `N` jobs, where each job starts at `start[i]` and ends at `end[i]` with a given `load[i]`, find the maximum CPU load at any time if all jobs are running on the same machine.

3. [Employee Free Time (hard)](https://www.codertrain.co/employee-free-time)
   Given a list `schedule`, where `schedule[i]` is a list of `Interval`s for the `i-th` employee, return a list of finite intervals representing the common, positive-length free time for all employees.

4. [Non-overlapping Intervals (medium)](https://leetcode.com/problems/non-overlapping-intervals/)
   Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

5. [Find Right Interval (medium)](https://leetcode.com/problems/find-right-interval/)
   Given a set of intervals, for each interval, find the minimum interval in the set which has a start point greater than or equal to the end point of that interval.

6. [Employee Free Time (hard)](https://leetcode.com/problems/employee-free-time/)
   Given a list of `schedule`, where `schedule[i]` is a list of intervals representing the working hours of the `i-th` employee, return the list of finite intervals representing common free time across all employees, sorted in order.

7. [Car Pooling (medium)](https://leetcode.com/problems/car-pooling/)
   You are driving a vehicle that can hold a limited number of passengers, and you have a trip list. Each trip has a start location, an end location, and a number of passengers. Determine if it is possible to pick up and drop off all passengers given the vehicle's capacity.

8. [Range Module (hard)](https://leetcode.com/problems/range-module/)
   A Range Module is a module that tracks ranges of numbers. Implement a class that supports adding, removing, and querying ranges.


### 5. Pattern: Cyclic Sort


#### Introduction
The Cyclic Sort pattern is a technique to sort an array in O(n) time complexity, where the array elements are in the range 1 to n. It involves iterating over the array and placing each element in its correct position, such that the element at index i is the value i + 1.

---

#### Basic Cyclic Sort Problems

1. [Cyclic Sort (easy)](https://www.geeksforgeeks.org/sort-an-array-which-contain-1-to-n-values-in-on-using-cycle-sort/)
   Given an array containing 1 to n distinct numbers, sort the array in-place using the cyclic sort algorithm.

2. [Find the Missing Number (easy)](https://leetcode.com/problems/missing-number/)
   Given an array containing n distinct numbers taken from 0 to n, find the one missing number in the range.

3. [Find all Missing Numbers (easy)](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)
   Given an array containing n distinct numbers taken from 1 to n, find all the missing numbers in the array.

4. [Find the Duplicate Number (easy)](https://leetcode.com/problems/find-the-duplicate-number/)
   Given an array containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.

5. [Find all Duplicate Numbers (easy)](https://leetcode.com/problems/find-all-duplicates-in-an-array/)
   Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once. Find all the elements that appear twice in this array.

6. [Set Mismatch (easy)](https://leetcode.com/problems/set-mismatch/)
   You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

---

#### Problem Challenges

1. [Find the Corrupt Pair (easy)](https://thecodingsimplified.com/find-currupt-pair/)
   Given an unsorted array containing n numbers taken from the range 1 to n, find the two numbers that are causing the corruption.

2. [Find the Smallest Missing Positive Number (medium)](https://leetcode.com/problems/first-missing-positive/)
   Given an unsorted integer array, find the smallest missing positive integer.

3. [Find the First K Missing Positive Numbers (hard)](https://thecodingsimplified.com/find-the-first-k-missing-positive-number/)
   Given an unsorted integer array, find the first K missing positive numbers.

4. [Kth Missing Positive Number (easy)](https://leetcode.com/problems/kth-missing-positive-number/)
   Given an array arr of positive integers sorted in a strictly increasing order, and an integer k. Find the kth positive integer that is missing from this array.

5. [Find All Numbers Disappeared in an Array (easy)](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)
   Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once. Find all the elements of [1, n] inclusive that do not appear in this array.

### 6. Pattern: In-place Reversal of a LinkedList

#### Introduction
The In-place Reversal of a LinkedList pattern involves reversing a LinkedList without using extra space. It typically requires changing the next pointers of the nodes to reverse the LinkedList.

---

#### Basic Problems

1. [Reverse a LinkedList (easy)](https://leetcode.com/problems/reverse-linked-list/)
   Reverse a singly linked list.

2. [Reverse a Sub-list (medium)](https://leetcode.com/problems/reverse-linked-list-ii/)
   Reverse the nodes of a linked list from position `m` to `n` in-place.

3. [Reverse every K-element Sub-list (medium)](https://leetcode.com/problems/reverse-nodes-in-k-group/)
   Given a linked list, reverse the nodes of a linked list `k` at a time and return its modified list.

4. [Middle of the Linked List (easy)](https://leetcode.com/problems/middle-of-the-linked-list/)
   Given a non-empty, singly linked list with head node `head`, return a middle node of linked list. If there are two middle nodes, return the second middle node.

---

#### Problem Challenges

1. [Reverse alternating K-element Sub-list (medium)](https://www.geeksforgeeks.org/reverse-alternate-k-nodes-in-a-singly-linked-list/)
   Given a linked list, write a function to reverse every alternate `k` nodes (where `k` is an input to the function) in an efficient way.

2. [Rotate a LinkedList (medium)](https://leetcode.com/problems/rotate-list/)
   Given a linked list, rotate the list to the right by `k` places, where `k` is a non-negative integer.

3. [Swap Nodes in Pairs (medium)](https://leetcode.com/problems/swap-nodes-in-pairs/)
   Given a linked list, swap every two adjacent nodes and return its head.

4. [Reverse Linked List II (medium)](https://leetcode.com/problems/reverse-linked-list-ii/)
   Reverse a linked list from position `m` to `n`. Do it in one-pass.

5. [Odd Even Linked List (medium)](https://leetcode.com/problems/odd-even-linked-list/)
   Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

### 7. Pattern: Stack

#### Introduction
The Stack pattern involves using a Last In First Out (LIFO) data structure to solve problems where elements need to be processed in a Last In First Out manner.

---

#### Basic Stack Problems

1. [Valid Parentheses (easy)](https://leetcode.com/problems/valid-parentheses/)
   Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

2. [Balanced Parentheses (easy)](https://leetcode.com/problems/valid-parentheses/)
   Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string has balanced parentheses.

3. [Reverse a String (easy)](https://leetcode.com/problems/reverse-string/)
   Write a function that reverses a string. The input string is given as an array of characters char[].

4. [Decimal to Binary Conversion (easy)](https://www.geeksforgeeks.org/decimal-binary-number-using-recursion/)
   Convert a decimal number to binary using recursion.

5. [Min Stack (easy)](https://leetcode.com/problems/min-stack/)
   Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

6. [Next Greater Element (easy)](https://leetcode.com/problems/next-greater-element-i/)
   You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

7. [Sorting a Stack (medium)](https://www.geeksforgeeks.org/sort-a-stack-using-recursion/)
   Given a stack, sort it using recursion without using any extra space.

8. [Simplify Path (medium)](https://leetcode.com/problems/simplify-path/)
   Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.


### 8. Pattern: Monotonic Stack

#### Introduction to Monotonic Stack
The Monotonic Stack pattern involves using a stack to maintain elements in either increasing or decreasing order. It is useful for solving problems where you need to find the next greater or smaller element, or maintain a monotonic sequence.

---

#### Basic Monotonic Stack Problems

1. [Next Greater Element (easy)](https://leetcode.com/problems/next-greater-element-i/)
   You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

2. [Daily Temperatures (easy)](https://leetcode.com/problems/daily-temperatures/)
   Given a list of daily temperatures, return a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

3. [Remove Nodes From Linked List (easy)](https://leetcode.com/problems/remove-linked-list-elements/)
   Remove all elements from a linked list of integers that have a specific value.

4. [Remove All Adjacent Duplicates In String (easy)](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/)
   Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

5. [Remove All Adjacent Duplicates in String II (medium)](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/)
   Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of the deleted substring to concatenate together.

6. [Remove K Digits (hard)](https://leetcode.com/problems/remove-k-digits/)
   Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

---

#### Additional Problems

1. [Next Greater Element II (medium)](https://leetcode.com/problems/next-greater-element-ii/)
   Given a circular array (the next element of the last element is the first element of the array), find the next greater number for every element.

2. [Daily Temperatures II (hard)](https://leetcode.com/problems/daily-temperatures/)
   Given a list of daily temperatures, return a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead. Consider the temperature is circular in nature i.e. the next day after the last day is the first day.

3. [Remove Duplicate Letters (hard)](https://leetcode.com/problems/remove-duplicate-letters/)
   Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.


### 9. Pattern: Hash Maps

#### Introduction
The Hash Maps pattern involves using a hash map or hash table to store and retrieve elements in constant time. It is useful for solving problems where you need to track frequencies of elements or maintain a mapping between two sets of elements.

---

#### Basic Hash Maps Problems

1. [First Non-repeating Character (easy)](https://leetcode.com/problems/first-unique-character-in-a-string/)
   Given a string, find the first non-repeating character in it and return its index. If it doesn't exist, return -1.

2. [Largest Unique Number (easy)](https://leetcode.com/problems/largest-unique-number/)
   Given an array of integers A, return the largest integer that only occurs once.

3. [Maximum Number of Balloons (easy)](https://leetcode.com/problems/maximum-number-of-balloons/)
   Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

4. [Longest Palindrome (easy)](https://leetcode.com/problems/longest-palindrome/)
   Given a string which consists of lowercase or uppercase letters, find the length of the longest palindrome that can be built with those letters.

5. [Ransom Note (easy)](https://leetcode.com/problems/ransom-note/)
   Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines; otherwise, it will return false.

---

#### Additional Hash Maps Problems

1. [Isomorphic Strings (easy)](https://leetcode.com/problems/isomorphic-strings/)
   Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic if the characters in s can be replaced to get t.

2. [Word Pattern (easy)](https://leetcode.com/problems/word-pattern/)
   Given a pattern and a string s, find if s follows the same pattern.

3. [Group Anagrams (medium)](https://leetcode.com/problems/group-anagrams/)
   Given an array of strings, group anagrams together.

4. [Minimum Index Sum of Two Lists (easy)](https://leetcode.com/problems/minimum-index-sum-of-two-lists/)
   Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings. You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement.


### 10. Pattern: Tree Breadth First Search

#### Introduction
The Tree Breadth First Search (BFS) pattern is used to traverse or search a tree level by level. It explores all the child nodes at the present depth before moving on to the nodes at the next depth level.

---

#### Basic Tree BFS Problems

1. [Binary Tree Level Order Traversal (easy)](https://leetcode.com/problems/binary-tree-level-order-traversal/)
   Given a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

2. [Reverse Level Order Traversal (easy)](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/)
   Given a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).

3. [Zigzag Traversal (medium)](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)
   Given a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

4. [Level Averages in a Binary Tree (easy)](https://leetcode.com/problems/average-of-levels-in-binary-tree/)
   Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

5. [Minimum Depth of a Binary Tree (easy)](https://leetcode.com/problems/minimum-depth-of-binary-tree/)
   Given a binary tree, find its minimum depth. The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

6. [Maximum Depth of a Binary Tree (easy)](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
   Given a binary tree, find its maximum depth. The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

7. [Level Order Successor (easy)](https://www.geeksforgeeks.org/level-order-successor-of-a-node-in-binary-tree/)
   Given a binary tree and a node, find the level order successor of the given node in the tree. The level order successor is the node that appears right after the given node in the level order traversal.

---

#### Additional Tree BFS Problems

1. [Connect Level Order Siblings (medium)](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/)
   Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

2. [Connect All Level Order Siblings (medium)](https://www.educative.io/m/connect-all-siblings)
   Connect all the adjacent nodes at the same level in a binary tree. The structure of the binary tree is that each node will have a next pointer to the right node.

3. [Right View of a Binary Tree (easy)](https://leetcode.com/problems/binary-tree-right-side-view/)
   Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

4. [Binary Tree Level Order Traversal - II (easy)](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/)
   Given a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).

5. [Average of Levels in Binary Tree (easy)](https://leetcode.com/problems/average-of-levels-in-binary-tree/)
   Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

6. [Minimum Depth of Binary Tree (easy)](https://leetcode.com/problems/minimum-depth-of-binary-tree/)
   Given a binary tree, find its minimum depth. The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

7. [Maximum Depth of Binary Tree (easy)](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
   Given a binary tree, find its maximum depth. The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

8. [Binary Tree Zigzag Level Order Traversal (medium)](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)
   Given a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

9. [Populating Next Right Pointers in Each Node (medium)](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/)
   You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

10. [Binary Tree Right Side View (medium)](https://leetcode.com/problems/binary-tree-right-side-view/)
    Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.


### 11. Pattern: Tree Depth First Search

#### Introduction
The Tree Depth First Search (DFS) pattern is used to traverse or search a tree recursively, visiting all the nodes of a tree systematically. There are three types of DFS traversal: Pre-order, In-order, and Post-order.

---

#### Basic Tree DFS Problems

1. [Binary Tree Path Sum (easy)](https://leetcode.com/problems/path-sum/)
   Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

2. [All Paths for a Sum (medium)](https://leetcode.com/problems/path-sum-iii/)
   Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

3. [Sum of Path Numbers (medium)](https://leetcode.com/problems/sum-root-to-leaf-numbers/)
   Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number. Find the total sum of all root-to-leaf numbers.

4. [Path With Given Sequence (medium)](https://www.geeksforgeeks.org/check-root-leaf-path-given-sequence/)
   Given a binary tree and an array representing a sequence, check if the sequence forms a root-to-leaf path in the binary tree.

---

#### Additional Tree DFS Problems

5. [Count Paths for a Sum (medium)](https://leetcode.com/problems/path-sum-iii/)
   Given a binary tree and a sum, find the total number of paths in the tree that sum to the given sum.

6. [Tree Diameter (medium)](https://leetcode.com/problems/diameter-of-binary-tree/)
   Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

7. [Path with Maximum Sum (hard)](https://leetcode.com/problems/binary-tree-maximum-path-sum/)
   Given a non-empty binary tree, find the maximum path sum. For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

---

#### Additional Similar LeetCode Problems

8. [Binary Tree Maximum Path Sum (hard)](https://leetcode.com/problems/binary-tree-maximum-path-sum/)
   Given a non-empty binary tree, find the maximum path sum. For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

9. [Flatten Binary Tree to Linked List (medium)](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/)
   Given a binary tree, flatten it to a linked list in-place. The linked list should use the same TreeNode structure where the right child pointer points to the next node in the list and the left child pointer is always NULL.

10. [Binary Tree Right Side View (medium)](https://leetcode.com/problems/binary-tree-right-side-view/)
    Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

11. [Binary Tree Paths (easy)](https://leetcode.com/problems/binary-tree-paths/)
    Given a binary tree, return all root-to-leaf paths.

12. [Path Sum II (medium)](https://leetcode.com/problems/path-sum-ii/)
    Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

13. [Symmetric Tree (easy)](https://leetcode.com/problems/symmetric-tree/)
    Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

14. [Sum Root to Leaf Numbers (medium)](https://leetcode.com/problems/sum-root-to-leaf-numbers/)
    Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.


### 12. Pattern: Graphs

#### Introduction to Graphs
Graphs are a fundamental data structure used to represent relationships between pairs of objects. They consist of vertices (nodes) connected by edges.

#### Graph Traversal: Depth First Search (DFS)
DFS is a traversal algorithm that visits the vertices of a graph recursively, going as far as possible along each branch before backtracking.

#### Graph Traversal: Breadth First Search (BFS)
BFS is a traversal algorithm that visits the vertices of a graph level by level, exploring all neighbors at the current level before moving to the next level.

---

#### Basic Graph Problems

1. [Find if Path Exists in Graph (easy)](https://leetcode.com/problems/find-whether-path-exists-in-graph/)
   Given a graph, determine if a path exists between two given vertices.

2. [Number of Provinces (medium)](https://leetcode.com/problems/number-of-provinces/)
   Given an adjacency matrix representing a graph, determine the number of connected components in the graph (provinces).

3. [Minimum Number of Vertices to Reach All Nodes (medium)](https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/)
   Given a directed acyclic graph (DAG) with n nodes, find all the reachable nodes from node 0 and return them in any order.

---

#### Additional Graph Problems

4. [Course Schedule (medium)](https://leetcode.com/problems/course-schedule/)
   There are a total of n courses you have to take, labeled from 0 to n-1. Some courses may have prerequisites, for example, to take course 0 you have to first take course 1, which is expressed as a pair: [0,1].

5. [Clone Graph (medium)](https://leetcode.com/problems/clone-graph/)
   Given a reference of a node in a connected undirected graph. Return a deep copy (clone) of the graph.

6. [Word Ladder (medium)](https://leetcode.com/problems/word-ladder/)
   Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    - Only one letter can be changed at a time.
    - Each transformed word must exist in the word list.

7. [Pacific Atlantic Water Flow (medium)](https://leetcode.com/problems/pacific-atlantic-water-flow/)
   Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

8. [Cheapest Flights Within K Stops (medium)](https://leetcode.com/problems/cheapest-flights-within-k-stops/)
   There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w. Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

9. [Network Delay Time (medium)](https://leetcode.com/problems/network-delay-time/)
   You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.


### 13. Pattern: Island (Matrix traversal)

1. Introduction to Island Pattern
2. Number of Islands (easy) [LeetCode](https://leetcode.com/problems/number-of-islands/) (200)
3. Biggest Island (easy)
4. Flood Fill (easy)
5. Number of Closed Islands (easy)
6. Problem Challenge 1 (easy)
7. Problem Challenge 2 (medium)
8. Problem Challenge 3 (medium)
9. Max Area of Island (Medium) [LeetCode](https://leetcode.com/problems/max-area-of-island/) (695)
10. Number of Distinct Islands (Medium) [LeetCode](https://leetcode.com/problems/number-of-distinct-islands/) (694)
11. Island Perimeter (Easy) [LeetCode](https://leetcode.com/problems/island-perimeter/) (463)
12. Minimum Number of Days to Disconnect Island (Hard) [LeetCode](https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island/) (1568)
13. Number of Enclaves (Medium) [LeetCode](https://leetcode.com/problems/number-of-enclaves/) (1020)
14. Number of Ships in a Rectangle (Hard) [LeetCode](https://leetcode.com/problems/number-of-ships-in-a-rectangle/) (1274)
15. Number of Provinces (Medium) [LeetCode](https://leetcode.com/problems/number-of-provinces/) (547)
16. Remove Islands (Medium) [LeetCode](https://leetcode.com/problems/remove-islands/) (1253)
17. Shortest Bridge (Medium) [LeetCode](https://leetcode.com/problems/shortest-bridge/) (934)
18. Surrounded Regions (Medium) [LeetCode](https://leetcode.com/problems/surrounded-regions/) (130)

### Backtracking

#### Introduction to Backtracking
Backtracking is a technique for solving problems by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point of time.

---

#### Backtracking Problems

1. N-Queens ([LeetCode 51](https://leetcode.com/problems/n-queens/))
   The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

2. House Robber III ([LeetCode 337](https://leetcode.com/problems/house-robber-iii/))
   The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

3. Restore IP Addresses ([LeetCode 93](https://leetcode.com/problems/restore-ip-addresses/))
   Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. You can return them in any order.

4. Flood Fill ([LeetCode 733](https://leetcode.com/problems/flood-fill/))
   An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and newColor. You should perform a flood fill on the image starting from the pixel image[sr][sc].

5. Sudoku Solver ([LeetCode 37](https://leetcode.com/problems/sudoku-solver/))
   Write a program to solve a Sudoku puzzle by filling the empty cells. Empty cells are indicated by the character '.'. You may assume that there will be only one unique solution.

---

#### Additional Backtracking Problems

6. Letter Combinations of a Phone Number ([LeetCode 17](https://leetcode.com/problems/letter-combinations-of-a-phone-number/))
   Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent on a phone pad.

7. Combination Sum ([LeetCode 39](https://leetcode.com/problems/combination-sum/))
   Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

8. Permutations ([LeetCode 46](https://leetcode.com/problems/permutations/))
   Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

9. Subsets ([LeetCode 78](https://leetcode.com/problems/subsets/))
   Given an integer array nums of unique elements, return all possible subsets (the power set). The solution set must not contain duplicate subsets. Return the solution in any order.

10. Word Search ([LeetCode 79](https://leetcode.com/problems/word-search/))
    Given an m x n board and a word, find if the word exists in the grid. The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

### 14. Pattern: Two Heaps

#### Introduction
The Two Heaps pattern involves using two heaps—one min-heap and one max-heap—to manage a set of elements in such a way that we can efficiently calculate the median of the elements.

---

#### Two Heaps Problems

1. Find the Median of a Number Stream (medium) [LeetCode 295](https://leetcode.com/problems/find-median-from-data-stream/)
   Design a class to calculate the median of a number stream. The class should have two methods, `insertNum(int num)` and `findMedian()`.

2. Sliding Window Median (hard) [LeetCode 480](https://leetcode.com/problems/sliding-window-median/)
   Given an array of integers nums and an integer k, return the median of the numbers in the window size k as it slides from left to right.

3. Maximize Capital (hard) [LeetCode 502](https://leetcode.com/problems/ipo/)
   You are given initial capital, a number of projects, and the maximum number of projects you can undertake. Each project has a capital and a profit. You need to choose the most profitable projects while maintaining a minimum capital at each step.

4. Maximum Sum Combinations (medium) [InterviewBit](https://www.interviewbit.com/problems/maximum-sum-combinations/)
   Given two equally sized arrays A, B of length N, find the maximum sum of products of elements from both arrays. You can swap elements between arrays A and B at most K times.

5. Find Median from Data Stream II (hard) [LeetCode 1488](https://leetcode.com/problems/avoid-flood-in-the-city/)
   Given an array of integers representing the heights of different lakes in a city, and an array of queries, each query consists of a positive integer k and a positive integer day. The height of the kth lake in day is reduced to zero, and you need to output the first day when any lake will overflow.

6. Avoid Flood in The City (medium) [LeetCode 1488](https://leetcode.com/problems/avoid-flood-in-the-city/)
   Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water. If it rains over a lake which is full of water, there will be a flood. Your goal is to avoid the flood in any lake.


### 15. Pattern: Subsets

#### Introduction
The Subsets pattern involves generating all possible subsets of a set.

---

#### Subsets Problems

1. Subsets (easy) [LeetCode 78](https://leetcode.com/problems/subsets/)
   Given a set of distinct integers, nums, return all possible subsets (the power set).

2. Subsets II (medium) [LeetCode 90](https://leetcode.com/problems/subsets-ii/)
   Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

3. Generalized Abbreviation (medium) [LeetCode 320](https://leetcode.com/problems/generalized-abbreviation/)
   Write a function to generate the generalized abbreviations of a word.

4. Letter Case Permutation (medium) [LeetCode 784](https://leetcode.com/problems/letter-case-permutation/)
   Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string. Return a list of all possible strings we could create.

5. Generate Parentheses (medium) [LeetCode 22](https://leetcode.com/problems/generate-parentheses/)
   Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

6. Word Search II (hard) [LeetCode 212](https://leetcode.com/problems/word-search-ii/)
   Given a 2D board and a list of words from the dictionary, find all words in the board.

7. Subsets With Target Sum (medium) [LeetCode 494](https://leetcode.com/problems/target-sum/)
   You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol. Find out how many ways to assign symbols to make sum of integers equal to target S.

8. Combination Sum (medium) [LeetCode 39](https://leetcode.com/problems/combination-sum/)
   Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

9. Combination Sum II (medium) [LeetCode 40](https://leetcode.com/problems/combination-sum-ii/)
   Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

10. Palindrome Partitioning (medium) [LeetCode 131](https://leetcode.com/problems/palindrome-partitioning/)
    Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.


### [16. Pattern: Modified Binary Search](binary-search/BinarySearch.md)

1. Introduction 
2. Order-agnostic Binary Search (easy) [Geeksforgeeks](https://www.geeksforgeeks.org/order-agnostic-binary-search/)
3. Ceiling of a Number (medium) [Geeksforgeeks-Ceil](https://www.geeksforgeeks.org/ceiling-in-a-sorted-array/) [Geeksforgeeks-Floor](https://www.geeksforgeeks.org/floor-in-a-sorted-array/)
4. Next Letter (medium) [Leetcode 744](https://leetcode.com/problems/find-smallest-letter-greater-than-target/)
5. Number Range (medium) [Leetcode 34](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
6. Search in a Sorted Infinite Array (medium) [Leetcode 702](https://www.geeksforgeeks.org/find-position-element-sorted-array-infinite-numbers/)
7. Minimum Difference Element (medium)  
   Given a sorted array, find the minimum difference between a given number and all numbers in the array.
8. Bitonic Array Maximum (easy) [Geeksforgeeks](https://www.geeksforgeeks.org/find-the-maximum-element-in-an-array-which-is-first-increasing-and-then-decreasing/)
9. Problem Challenge 1: Search Bitonic Array (medium) [Leetcode 1095](https://leetcode.com/problems/find-in-mountain-array/)
10. Problem Challenge 2: Search in Rotated Array (medium) [Leetcode 33](https://leetcode.com/problems/search-in-rotated-sorted-array/)
11. Problem Challenge 3: Rotation Count (medium) [Geeksforgeeks](https://www.geeksforgeeks.org/find-rotation-count-rotated-sorted-array/)
12. Search a 2D Matrix (medium) [Leetcode 74](https://leetcode.com/problems/search-a-2d-matrix/)
13. Minimum Number of Days to Make m Bouquets (medium) [Leetcode 1482](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/)
14. Koko Eating Bananas (medium) [Leetcode 875](https://leetcode.com/problems/koko-eating-bananas/)
15. Capacity To Ship Packages Within D Days (medium) [Leetcode 1011](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/)
16. Median of Two Sorted Arrays (hard) [Leetcode 4](https://leetcode.com/problems/median-of-two-sorted-arrays/)
17. Single Element in a Sorted Array (medium) [Leetcode 540](https://leetcode.com/problems/single-element-in-a-sorted-array/)
18. Find Minimum in Rotated Sorted Array (medium) [Leetcode 153](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
19. Find Peak Element (medium) [Leetcode 162](https://leetcode.com/problems/find-peak-element/)
20. Search in Rotated Sorted Array II (medium) [Leetcode 81](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/)

### 17. Pattern: Bitwise XOR

1. Introduction
2. Single Number (easy)
3. Two Single Numbers (medium)
4. Complement of Base 10 Number (medium)
5. Problem Challenge 1: Flip and Invert an Image (hard)


### 18. Pattern: Top 'K' Elements

1. Introduction
2. Top 'K' Numbers (easy) [Solution](13.-pattern-top-k-elements/02.top-k-numbers.md)
3. Kth Smallest Number (easy)
4. 'K' Closest Points to the Origin (easy) [Leetcode](https://leetcode.com/problems/k-closest-points-to-origin/)
5. Connect Ropes (easy)
6. Top 'K' Frequent Numbers (medium)
7. Frequency Sort (medium)
8. Kth Largest Number in a Stream (medium) [Leetcode](https://leetcode.com/problems/kth-largest-element-in-a-stream/)
9. 'K' Closest Numbers (medium)
10. Maximum Distinct Elements (medium)
11. Sum of Elements (medium)
12. Rearrange String (hard)
13. Problem Challenge 1: Rearrange String K Distance Apart (hard)
14. Problem Challenge 2: Scheduling Tasks (hard)
15. Problem Challenge 3: Frequency Stack (hard)
16. Minimum Cost to Connect Ropes (medium) [Leetcode](https://leetcode.com/problems/minimum-cost-to-connect-sticks/)
17. Find K Pairs with Smallest Sums (medium) [Leetcode](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/)
18. Maximum Frequency Stack (hard) [Leetcode](https://leetcode.com/problems/maximum-frequency-stack/)
19. Find K-th Smallest Pair Distance (hard) [Leetcode](https://leetcode.com/problems/find-k-th-smallest-pair-distance/)
20. Find the Kth Largest Integer in the Array (medium) [Leetcode](https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/)
21. Kth Largest Element in an Array (medium) [Leetcode](https://leetcode.com/problems/kth-largest-element-in-an-array/)
22. Sort Characters By Frequency (medium) [Leetcode](https://leetcode.com/problems/sort-characters-by-frequency/)
23. Task Scheduler (medium) [Leetcode](https://leetcode.com/problems/task-scheduler/)
24. K Closest Points to Origin (medium) [Leetcode](https://leetcode.com/problems/k-closest-points-to-origin/)
25. Maximum Points You Can Obtain from Cards (medium) [Leetcode](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/)


### 19. Pattern: K-way merge

1. Introduction
2. Merge K Sorted Lists (medium) [Leetcode](https://leetcode.com/problems/merge-k-sorted-lists/)
3. Kth Smallest Number in M Sorted Lists (Medium)
4. Kth Smallest Number in a Sorted Matrix (Hard) [Educative.io](https://www.educative.io/courses/grokking-the-coding-interview/x1NJVYKNvqz)
5. Smallest Number Range (Hard)
6. Problem Challenge 1: K Pairs with Largest Sums (hard)
7. Problem Challenge 2: Smallest Number Range (hard) [Leetcode](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/)
8. Problem Challenge 3: Smallest Range (hard) [Leetcode](https://leetcode.com/problems/smallest-range/)
9. Problem Challenge 4: Maximum Distinct Elements (medium) [Leetcode](https://leetcode.com/problems/maximize-number-of-nice-divisors/)
10. Problem Challenge 5: Count of Smaller Numbers After Self (hard) [Leetcode](https://leetcode.com/problems/count-of-smaller-numbers-after-self/)
11. Problem Challenge 6: Divide Array in Sets of K Consecutive Numbers (medium) [Leetcode](https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/)
12. Problem Challenge 7: Find K-th Smallest Pair Distance (hard) [Leetcode](https://leetcode.com/problems/find-k-th-smallest-pair-distance/)
13. Problem Challenge 8: Maximize Distance to Closest Person (medium) [Leetcode](https://leetcode.com/problems/maximize-distance-to-closest-person/)
14. Problem Challenge 9: Minimum Window Subsequence (hard) [Leetcode](https://leetcode.com/problems/minimum-window-subsequence/)
15. Problem Challenge 10: Kth Smallest Element in a Sorted Matrix (medium) [Leetcode](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/)
16. Problem Challenge 11: Find K Closest Elements (medium) [Leetcode](https://leetcode.com/problems/find-k-closest-elements/)
17. Problem Challenge 12: Smallest String With Swaps (medium) [Leetcode](https://leetcode.com/problems/smallest-string-with-swaps/)
18. Problem Challenge 13: Minimum Number of Refueling Stops (hard) [Leetcode](https://leetcode.com/problems/minimum-number-of-refueling-stops/)
19. Problem Challenge 14: Minimum Cost to Connect Sticks (medium) [Leetcode](https://leetcode.com/problems/minimum-cost-to-connect-sticks/)
20. Problem Challenge 15: Find K-th Smallest Pair Distance (hard) [Leetcode](https://leetcode.com/problems/find-k-th-smallest-pair-distance/)
21. Problem Challenge 16: Swim in Rising Water (hard) [Leetcode](https://leetcode.com/problems/swim-in-rising-water/)
22. Problem Challenge 17: Minimum Difficulty of a Job Schedule (hard) [Leetcode](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/)
23. Problem Challenge 18: Find K-Length Substrings With No Repeated Characters (medium) [Leetcode](https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/)
24. Problem Challenge 19: Maximum Performance of a Team (hard) [Leetcode](https://leetcode.com/problems/maximum-performance-of-a-team/)
25. Problem Challenge 20: Maximum Number of Events That Can Be Attended II (hard) [Leetcode](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/)



### 20. Pattern: Greedy Algorithms

1. Introduction to Greedy Algorithm
2. Valid Palindrome II (easy)
3. Maximum Length of Pair Chain (medium)
4. Minimum Add to Make Parentheses Valid (medium)
5. Remove Duplicate Letters (medium)
6. Largest Palindromic Number (Medium)
7. Removing Minimum and Maximum From Array (medium)
8. Problem Challenge 1: Partition Labels (medium) [LeetCode](https://leetcode.com/problems/partition-labels/)
9. Problem Challenge 2: Task Scheduler (medium) [LeetCode](https://leetcode.com/problems/task-scheduler/)
10. Problem Challenge 3: Minimum Number of Arrows to Burst Balloons (medium) [LeetCode](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/)
11. Problem Challenge 4: Queue Reconstruction by Height (medium) [LeetCode](https://leetcode.com/problems/queue-reconstruction-by-height/)
12. Problem Challenge 5: Lemonade Change (easy) [LeetCode](https://leetcode.com/problems/lemonade-change/)
13. Problem Challenge 6: Jump Game (medium) [LeetCode](https://leetcode.com/problems/jump-game/)
14. Problem Challenge 7: Candy (hard) [LeetCode](https://leetcode.com/problems/candy/)
15. Problem Challenge 8: Gas Station (medium) [LeetCode](https://leetcode.com/problems/gas-station/)
16. Problem Challenge 9: Best Time to Buy and Sell Stock II (easy) [LeetCode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)
17. Problem Challenge 10: Best Time to Buy and Sell Stock IV (hard) [LeetCode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/)
18. Problem Challenge 11: Assign Cookies (easy) [LeetCode](https://leetcode.com/problems/assign-cookies/)
19. Problem Challenge 12: Non-overlapping Intervals (medium) [LeetCode](https://leetcode.com/problems/non-overlapping-intervals/)
20. Problem Challenge 13: Queue Reconstruction by Height (medium) [LeetCode](https://leetcode.com/problems/queue-reconstruction-by-height/)
21. Problem Challenge 14: Jump Game II (medium) [LeetCode](https://leetcode.com/problems/jump-game-ii/)
22. Problem Challenge 15: Jump Game III (medium) [LeetCode](https://leetcode.com/problems/jump-game-iii/)
23. Problem Challenge 16: Candy Crush (medium) [LeetCode](https://leetcode.com/problems/candy-crush/)
24. Problem Challenge 17: Patching Array (hard) [LeetCode](https://leetcode.com/problems/patching-array/)
25. Problem Challenge 18: Minimum Domino Rotations For Equal Row (medium) [LeetCode](https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/)
26. Problem Challenge 19: Maximum Points You Can Obtain from Cards (medium) [LeetCode](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/)
27. Problem Challenge 20: Non-decreasing Array (medium) [LeetCode](https://leetcode.com/problems/non-decreasing-array/)



### 21. Pattern : 0/1 Knapsack (Dynamic Programming)

1. **Introduction**
2. [0/1 Knapsack (medium)](https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/)
3. [Equal Subset Sum Partition (medium)](https://leetcode.com/problems/partition-equal-subset-sum/) - LeetCode 416
4. [Subset Sum (medium)](https://www.geeksforgeeks.org/subset-sum-problem-dp-25/)
5. [Minimum Subset Sum Difference (hard)](https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/)
6. Problem Challenge 1: [Count of Subset Sum (hard)](https://leetcode.com/problems/target-sum/) - LeetCode 494
7. Problem Challenge 2: [Target Sum (hard)](https://leetcode.com/problems/partition-equal-subset-sum/) - LeetCode 416
8. Problem Challenge 3: [Combination Sum IV (medium)](https://leetcode.com/problems/combination-sum-iv/) - LeetCode 377
9. Problem Challenge 4: [Partition to K Equal Sum Subsets (medium)](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/) - LeetCode 698
10. Problem Challenge 5: [Minimum Cost For Tickets (medium)](https://leetcode.com/problems/minimum-cost-for-tickets/) - LeetCode 983
11. Problem Challenge 6: [Coin Change (medium)](https://leetcode.com/problems/coin-change/) - LeetCode 322
12. Problem Challenge 7: [Maximum Vacation Days (hard)](https://leetcode.com/problems/maximum-vacation-days/) - LeetCode 568
13. Problem Challenge 8: [Cherry Pickup II (hard)](https://leetcode.com/problems/cherry-pickup-ii/) - LeetCode 1463
14. Problem Challenge 9: [Stone Game IV (hard)](https://leetcode.com/problems/stone-game-iv/) - LeetCode 1510
15. Problem Challenge 10: [Out of Boundary Paths (medium)](https://leetcode.com/problems/out-of-boundary-paths/) - LeetCode 576
16. Problem Challenge 11: [Number of Music Playlists (hard)](https://leetcode.com/problems/number-of-music-playlists/) - LeetCode 920
17. Problem Challenge 12: [Out of Boundary Paths (medium)](https://leetcode.com/problems/out-of-boundary-paths/) - LeetCode 576
18. Problem Challenge 13: [Out of Boundary Paths (medium)](https://leetcode.com/problems/out-of-boundary-paths/) - LeetCode 576
19. Problem Challenge 14: [Out of Boundary Paths (medium)](https://leetcode.com/problems/out-of-boundary-paths/) - LeetCode 576
20. Problem Challenge 15: [Out of Boundary Paths (medium)](https://leetcode.com/problems/out-of-boundary-paths/) - LeetCode 576
21. Problem Challenge 16: [Paint House II (hard)](https://leetcode.com/problems/paint-house-ii/) - LeetCode 265
22. Problem Challenge 17: [Minimum Difficulty of a Job Schedule (hard)](https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/) - LeetCode 1335
23. Problem Challenge 18: [Number of Dice Rolls With Target Sum (medium)](https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/) - LeetCode 1155
24. Problem Challenge 19: [Best Time to Buy and Sell Stock IV (hard)](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/) - LeetCode 188
25. Problem Challenge 20: [Count Vowels Permutation (hard)](https://leetcode.com/problems/count-vowels-permutation/) - LeetCode 1220
26. Problem Challenge 21: [Maximum Points You Can Obtain from Cards (medium)](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/) - LeetCode 1423
27. Problem Challenge 22: [Delete and Earn (medium)](https://leetcode.com/problems/delete-and-earn/) - LeetCode 740
28. Problem Challenge 23: [Stone Game VI (medium)](https://leetcode.com/problems/stone-game-vi/) - LeetCode 1686
29. Problem Challenge 24: [Burst Balloons (hard)](https://leetcode.com/problems/burst-balloons/) - LeetCode 312
30. Problem Challenge 25: [Strange Printer (hard)](https://leetcode.com/problems/strange-printer/) - LeetCode 664



### 22. Pattern: Backtracking

1. **Introduction to Backtracking Pattern**
2. LeetCode [39. Combination Sum (medium)](https://leetcode.com/problems/combination-sum/)
3. LeetCode [79. Word Search (medium)](https://leetcode.com/problems/word-search/)
4. LeetCode [37. Sudoku Solver (hard)](https://leetcode.com/problems/sudoku-solver/)
5. LeetCode [254. Factor Combinations (medium)](https://leetcode.com/problems/factor-combinations/)
6. LeetCode [1593. Split a String Into the Max Number of Unique Substrings (medium)](https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/)
7. LeetCode [46. Permutations (medium)](https://leetcode.com/problems/permutations/)
8. LeetCode [17. Letter Combinations of a Phone Number (medium)](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)
9. LeetCode [22. Generate Parentheses (medium)](https://leetcode.com/problems/generate-parentheses/)
10. LeetCode [93. Restore IP Addresses (medium)](https://leetcode.com/problems/restore-ip-addresses/)
11. LeetCode [282. Expression Add Operators (hard)](https://leetcode.com/problems/expression-add-operators/)
12. LeetCode [131. Palindrome Partitioning (medium)](https://leetcode.com/problems/palindrome-partitioning/)
13. LeetCode [40. Combination Sum II (medium)](https://leetcode.com/problems/combination-sum-ii/)
14. LeetCode [47. Permutations II (medium)](https://leetcode.com/problems/permutations-ii/)
15. LeetCode [667. Beautiful Arrangement II (medium)](https://leetcode.com/problems/beautiful-arrangement-ii/)
16. LeetCode [491. Increasing Subsequences (medium)](https://leetcode.com/problems/increasing-subsequences/)
17. LeetCode [698. Partition to K Equal Sum Subsets (medium)](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/)
18. LeetCode [425. Word Squares (hard)](https://leetcode.com/problems/word-squares/)
19. LeetCode [842. Split Array into Fibonacci Sequence (medium)](https://leetcode.com/problems/split-array-into-fibonacci-sequence/)
20. LeetCode [140. Word Break II (hard)](https://leetcode.com/problems/word-break-ii/)
21. LeetCode [51. N-Queens (hard)](https://leetcode.com/problems/n-queens/)
22. LeetCode [77. Combinations (medium)](https://leetcode.com/problems/combinations/)
23. LeetCode [78. Subsets (medium)](https://leetcode.com/problems/subsets/)
24. LeetCode [90. Subsets II (medium)](https://leetcode.com/problems/subsets-ii/)
25. LeetCode [647. Palindromic Substrings (medium)](https://leetcode.com/problems/palindromic-substrings/)
26. LeetCode [491. Increasing Subsequences (medium)](https://leetcode.com/problems/increasing-subsequences/)
27. LeetCode [658. Find K Closest Elements (medium)](https://leetcode.com/problems/find-k-closest-elements/)
28. LeetCode [784. Letter Case Permutation (medium)](https://leetcode.com/problems/letter-case-permutation/)
29. LeetCode [1239. Maximum Length of a Concatenated String with Unique Characters (medium)](https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/)
30. LeetCode [60. Permutation Sequence (medium)](https://leetcode.com/problems/permutation-sequence/)


### 23. Pattern: Trie

1. **Introduction to Trie**
2. LeetCode [208. Implement Trie (Prefix Tree) (medium)](https://leetcode.com/problems/implement-trie-prefix-tree/)
3. LeetCode [1065. Index Pairs of a String (easy)](https://leetcode.com/problems/index-pairs-of-a-string/)
4. LeetCode [211. Design Add and Search Words Data Structure (medium)](https://leetcode.com/problems/design-add-and-search-words-data-structure/)
5. LeetCode [1055. Shortest Way to Form String (medium)](https://leetcode.com/problems/shortest-way-to-form-string/)
6. LeetCode [1265. Print Immutable Linked List in Reverse (medium)](https://leetcode.com/problems/print-immutable-linked-list-in-reverse/)
7. LeetCode [212. Word Search II (hard)](https://leetcode.com/problems/word-search-ii/)
8. LeetCode [720. Longest Word in Dictionary (easy)](https://leetcode.com/problems/longest-word-in-dictionary/)
9. LeetCode [642. Design Search Autocomplete System (hard)](https://leetcode.com/problems/design-search-autocomplete-system/)
10. LeetCode [642. Design Search Autocomplete System (hard)](https://leetcode.com/problems/design-search-autocomplete-system/)
11. LeetCode [1480. Running Sum of 1d Array (easy)](https://leetcode.com/problems/running-sum-of-1d-array/)
12. LeetCode [421. Maximum XOR of Two Numbers in an Array (medium)](https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/)
13. LeetCode [1032. Stream of Characters (hard)](https://leetcode.com/problems/stream-of-characters/)
14. LeetCode [736. Parse Lisp Expression (hard)](https://leetcode.com/problems/parse-lisp-expression/)
15. LeetCode [1707. Maximum XOR With an Element From Array (hard)](https://leetcode.com/problems/maximum-xor-with-an-element-from-array/)
16. LeetCode [1178. Number of Valid Words for Each Puzzle (hard)](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/)
17. LeetCode [336. Palindrome Pairs (hard)](https://leetcode.com/problems/palindrome-pairs/)
18. LeetCode [820. Short Encoding of Words (medium)](https://leetcode.com/problems/short-encoding-of-words/)
19. LeetCode [1395. Count Number of Teams (medium)](https://leetcode.com/problems/count-number-of-teams/)
20. LeetCode [648. Replace Words (medium)](https://leetcode.com/problems/replace-words/)


### 24. Pattern: Topological Sort (Graph)

### 1. Introduction to Topological Sort
2. LeetCode [210. Course Schedule II (medium)](https://leetcode.com/problems/course-schedule-ii/)
3. LeetCode [630. Course Schedule III (hard)](https://leetcode.com/problems/course-schedule-iii/)
4. LeetCode [444. Sequence Reconstruction (medium)](https://leetcode.com/problems/sequence-reconstruction/)
5. LeetCode [269. Alien Dictionary (hard)](https://leetcode.com/problems/alien-dictionary/)
6. LeetCode [207. Course Schedule (medium)](https://leetcode.com/problems/course-schedule/)
7. LeetCode [802. Find Eventual Safe States (medium)](https://leetcode.com/problems/find-eventual-safe-states/)
8. LeetCode [329. Longest Increasing Path in a Matrix (hard)](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/)
9. LeetCode [310. Minimum Height Trees (medium)](https://leetcode.com/problems/minimum-height-trees/)
10. LeetCode [1203. Sort Items by Groups Respecting Dependencies (hard)](https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/)
11. LeetCode [269. Alien Dictionary (hard)](https://leetcode.com/problems/alien-dictionary/)
12. LeetCode [444. Sequence Reconstruction (medium)](https://leetcode.com/problems/sequence-reconstruction/)
13. LeetCode [444. Sequence Reconstruction (medium)](https://leetcode.com/problems/sequence-reconstruction/)
14. LeetCode [444. Sequence Reconstruction (medium)](https://leetcode.com/problems/sequence-reconstruction/)
15. LeetCode [444. Sequence Reconstruction (medium)](https://leetcode.com/problems/sequence-reconstruction/)
16. LeetCode [444. Sequence Reconstruction (medium)](https://leetcode.com/problems/sequence-reconstruction/)
17. LeetCode [444. Sequence Reconstruction (medium)](https://leetcode.com/problems/sequence-reconstruction/)
18. LeetCode [444. Sequence Reconstruction (medium)](https://leetcode.com/problems/sequence-reconstruction/)
19. LeetCode [444. Sequence Reconstruction (medium)](https://leetcode.com/problems/sequence-reconstruction/)
20. LeetCode [444. Sequence Reconstruction (medium)](https://leetcode.com/problems/sequence-reconstruction/)


### 25. Pattern: Union Find

1. Introduction to Union Find Pattern
2. Redundant Connection (medium)
3. Number of Provinces (medium)
4. Is Graph Bipartite? (medium)
5. Path With Minimum Effort (medium)


### 26. Ordered Set

1. Merge Intervals (Medium) [LeetCode](https://leetcode.com/problems/merge-intervals/)
2. Insert Interval (Hard) [LeetCode](https://leetcode.com/problems/insert-interval/)
3. Meeting Rooms II (Medium) [LeetCode](https://leetcode.com/problems/meeting-rooms-ii/)
4. My Calendar II (Medium) [LeetCode](https://leetcode.com/problems/my-calendar-ii/)
5. Employee Free Time (Hard) [LeetCode](https://leetcode.com/problems/employee-free-time/)
6. Partition Labels (Medium) [LeetCode](https://leetcode.com/problems/partition-labels/)
7. Find Right Interval (Medium) [LeetCode](https://leetcode.com/problems/find-right-interval/)
8. Merge Intervals II (Hard) [LeetCode](https://leetcode.com/problems/merge-intervals-ii/)
9. Interval List Intersections (Medium) [LeetCode](https://leetcode.com/problems/interval-list-intersections/)
10. Summary Ranges (Medium) [LeetCode](https://leetcode.com/problems/summary-ranges/)
11. Employee Free Time (Hard) [LeetCode](https://leetcode.com/problems/employee-free-time/)
12. My Calendar III (Hard) [LeetCode](https://leetcode.com/problems/my-calendar-iii/)
13. Remove Interval (Medium) [LeetCode](https://leetcode.com/problems/remove-interval/)
14. Data Stream as Disjoint Intervals (Hard) [LeetCode](https://leetcode.com/problems/data-stream-as-disjoint-intervals/)
15. Range Module (Hard) [LeetCode](https://leetcode.com/problems/range-module/)
16. My Calendar II (Medium) [LeetCode](https://leetcode.com/problems/my-calendar-ii/)
17. Summary Ranges (Medium) [LeetCode](https://leetcode.com/problems/summary-ranges/)
18. Insert Interval (Medium) [LeetCode](https://leetcode.com/problems/insert-interval/)
19. Merge Intervals (Medium) [LeetCode](https://leetcode.com/problems/merge-intervals/)
20. Meeting Rooms II (Medium) [LeetCode](https://leetcode.com/problems/meeting-rooms-ii/)

### 27. Pattern: Multi-thread

1. Introduction to Multi-threaded Pattern
2. Same Tree (medium)
3. Invert Binary Tree (medium)
4. Binary Search Tree Iterator (medium)


### 29. Pattern:. Topological Sort
1. Alien Dictionary (Hard) [LeetCode](https://leetcode.com/problems/alien-dictionary/)
2. Course Schedule (Medium) [LeetCode](https://leetcode.com/problems/course-schedule/)
3. Course Schedule II (Medium) [LeetCode](https://leetcode.com/problems/course-schedule-ii/)
4. Sequence Reconstruction (Medium) [LeetCode](https://leetcode.com/problems/sequence-reconstruction/)
5. Rank Teams by Votes (Medium) [LeetCode](https://leetcode.com/problems/rank-teams-by-votes/)
6. Minimum Height Trees (Medium) [LeetCode](https://leetcode.com/problems/minimum-height-trees/)
7. Parallel Courses (Hard) [LeetCode](https://leetcode.com/problems/parallel-courses/)
8. Reconstruct Itinerary (Medium) [LeetCode](https://leetcode.com/problems/reconstruct-itinerary/)
9. Course Schedule III (Hard) [LeetCode](https://leetcode.com/problems/course-schedule-iii/)
10. Longest Increasing Path in a Matrix (Hard) [LeetCode](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/)

### 30. Pattern:. Knowing What to Track
The "Knowing What to Track" pattern involves identifying and maintaining specific states, values, or elements throughout the computation to efficiently solve a problem. This often requires data structures like hash maps, sets, or stacks.

---

#### [Palindrome Permutation](https://leetcode.com/problems/palindrome-permutation/)
Given a string, determine if a permutation of the string could form a palindrome.

---

#### [Valid Anagram](https://leetcode.com/problems/valid-anagram/)
Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

---

#### [Design Tic-Tac-Toe](https://leetcode.com/problems/design-tic-tac-toe/)
Design a Tic-tac-toe game that is played between two players on a `n x n` grid.

---

#### [Group Anagrams](https://leetcode.com/problems/group-anagrams/)
Given an array of strings `strs`, group the anagrams together.

---

#### [Maximum Frequency Stack](https://leetcode.com/problems/maximum-frequency-stack/)
Implement FreqStack, a class which simulates the operation of a stack-like data structure.

---

#### [First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/)
Given a string `s`, find the first non-repeating character in it and return its index. If it does not exist, return `-1`.

---

#### [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/)
Given two strings `s` and `p`, return an array of all the start indices of `p`'s anagrams in `s`. You may return the answer in any order.

---

#### [Longest Palindrome by Concatenating Two-Letter Words](https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/)
You are given an array of strings `words`. Each element of `words` consists of two lowercase English letters. Create the longest possible palindrome by selecting some elements from `words` and concatenating them in any order and return the length of the palindrome.

---

#### [Ransom Note](https://leetcode.com/problems/ransom-note/)
Given two strings `ransomNote` and `magazine`, return `true` if `ransomNote` can be constructed by using the letters from `magazine` and `false` otherwise.
1. Kth Smallest Number (hard)



### 31. Pattern: Union Find

#### Introduction to Union Find
The Union Find data structure, also known as Disjoint Set Union (DSU), is used to manage a partition of a set into disjoint (non-overlapping) subsets. It supports two main operations: union (joining two subsets into a single subset) and find (determining which subset a particular element is in).

---

#### [Redundant Connection](https://leetcode.com/problems/redundant-connection/)
In this problem, a tree with `n` nodes is given. The tree is represented as a set of edges. One additional edge is added to the tree to form a cycle. Return the edge that can be removed so that the resulting graph is a tree of `n` nodes.

---

#### [Number of Islands](https://leetcode.com/problems/number-of-islands/)
Given a 2D grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.

---

#### [Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/)
On a 2D plane, we place `n` stones at some integer coordinate points. Each coordinate point may have at most one stone. A stone can be removed if it shares either the same row or the same column as another stone that has not been removed. Find the largest possible number of stones that can be removed.

---

#### [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)
Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence.

---

#### [Last Day Where You Can Still Cross](https://leetcode.com/problems/last-day-where-you-can-still-cross/)
There is a `1`-based binary matrix where `0` represents land and `1` represents water. You are given an integer `row` and an array `cells` where `cells[i] = [ri, ci]` represents that the cell at `[ri, ci]` will be flooded in the `i-th` day. Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.

---

#### [Regions Cut by Slashes](https://leetcode.com/problems/regions-cut-by-slashes/)
An `n x n` grid is composed of `1 x 1` squares, and each square consists of a single backslash (`\`), forward slash (`/`), or blank space (` `). Return the number of regions created by slashes.

---

#### [Accounts Merge](https://leetcode.com/problems/accounts-merge/)
Given a list of accounts where each element `accounts[i]` is a list of strings, where the first element `accounts[i][0]` is a name, and the rest of the elements are emails. Merge the accounts, and return the accounts in the same format.

---

#### [Minimize Malware Spread](https://leetcode.com/problems/minimize-malware-spread/)
In a network of nodes, each node `i` is directly connected to another node `j` if and only if `graph[i][j] = 1`. Some nodes are initially infected by malware. When a node is infected, it will spread the malware to all the nodes that are directly connected to it. Find the node that if removed, would minimize the spread of the malware.

---

#### [Evaluate Division](https://leetcode.com/problems/evaluate-division/)
You are given equations in the format `A / B = k`, where `A` and `B` are variables represented as strings, and `k` is a real number (floating-point number). Given some queries, return the answers. If the answer does not exist, return `-1.0`.

### 32. Pattern: Custom Data Structures

#### Introduction to Custom Data Structures
Custom data structures involve designing and implementing specialized data structures to solve specific problems efficiently. These structures are often used when built-in data structures do not meet the performance requirements.

---

#### [Snapshot Array](https://leetcode.com/problems/snapshot-array/)
Implement a SnapshotArray that supports taking a snapshot of an array and retrieving the value of the array at a given index for a specific snapshot.

---

#### [Time-Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/)
Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a specific time.

---

#### [Implement LRU Cache](https://leetcode.com/problems/lru-cache/)
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

---

#### [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/)
Design a data structure that supports all following operations in average O(1) time: insert, delete, and getRandom.

---

#### [Min Stack](https://leetcode.com/problems/min-stack/)
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

---

#### [LFU Cache](https://leetcode.com/problems/lfu-cache/)
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

### 33. Pattern: Bitwise Manipulation

#### Introduction to Bitwise Manipulation
Bitwise manipulation involves using bitwise operators to perform operations directly on the binary representations of integers. These operations are often more efficient than their arithmetic or logical counterparts.

---

#### [Find the Difference](https://leetcode.com/problems/find-the-difference/)
Given two strings `s` and `t` which consist of only lowercase letters. String `t` is generated by randomly shuffling string `s` and then adding one more letter at a random position. Find the letter that was added in `t`.

---

#### [Complement of Base 10 Number](https://leetcode.com/problems/complement-of-base-10-integer/)
Every non-negative integer `N` has a binary representation. The complement of this binary representation is another binary number where each bit is flipped (0 to 1 and 1 to 0). Return the complement of `N`.

---

#### [Flipping an Image](https://leetcode.com/problems/flipping-an-image/)
Given a binary matrix, flip the image horizontally, then invert it.

---

#### [Single Number](https://leetcode.com/problems/single-number/)
Given a non-empty array of integers `nums`, every element appears twice except for one. Find that single one.

---

#### [Two Single Numbers](https://leetcode.com/problems/single-number-iii/)
Given an array of numbers `nums`, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

---

#### [Encode and Decode Strings](https://leetcode.com/problems/encode-and-decode-strings/)
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

---

#### [Reverse Bits](https://leetcode.com/problems/reverse-bits/)
Reverse the bits of a given 32 bits unsigned integer.


### 34. Dynamic Programming
Great articles to learn the general dynamic programming knowledge & patterns
https://leetcode.com/discuss/general-discussion/458695/Dynamic-Programming-Patterns
https://leetcode.com/discuss/general-discussion/662866/DP-for-Beginners-Problems-or-Patterns-or-Sample-Solutions
https://leetcode.com/discuss/general-discussion/1050391/Must-do-Dynamic-programming-Problems-Catefory-wise
Table version of the list: https://chuka231.blogspot.com/2021/01/leetcode-all-dynamic-programming.html

1.Linear DP
https://leetcode.com/problems/climbing-stairs/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
https://leetcode.com/problems/min-cost-climbing-stairs/
https://leetcode.com/problems/divisor-game/
https://leetcode.com/problems/decode-ways/
https://leetcode.com/problems/unique-binary-search-trees/
https://leetcode.com/problems/house-robber/
https://leetcode.com/problems/perfect-squares/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
https://leetcode.com/problems/coin-change/
https://leetcode.com/problems/counting-bits/
https://leetcode.com/problems/integer-break/
https://leetcode.com/problems/count-numbers-with-unique-digits/
https://leetcode.com/problems/wiggle-subsequence/
https://leetcode.com/problems/partition-equal-subset-sum/
https://leetcode.com/problems/maximum-length-of-pair-chain/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
https://leetcode.com/problems/delete-and-earn/
https://leetcode.com/problems/domino-and-tromino-tiling/
https://leetcode.com/problems/knight-dialer/
https://leetcode.com/problems/minimum-cost-for-tickets/
https://leetcode.com/problems/partition-array-for-maximum-sum/
https://leetcode.com/problems/filling-bookcase-shelves/
https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/
https://leetcode.com/problems/greatest-sum-divisible-by-three/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
https://leetcode.com/problems/student-attendance-record-ii/
https://leetcode.com/problems/decode-ways-ii/
https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero/
https://leetcode.com/problems/maximum-profit-in-job-scheduling/
https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/
https://leetcode.com/problems/stone-game-iii/
https://leetcode.com/problems/restore-the-array/
https://leetcode.com/problems/form-largest-integer-with-digits-that-add-up-to-target/
https://leetcode.com/problems/stone-game-iv/
https://leetcode.com/problems/coin-change-2/

2.Knapsack
https://leetcode.com/problems/house-robber-ii/
https://leetcode.com/problems/ones-and-zeroes/
https://leetcode.com/problems/target-sum/
https://leetcode.com/problems/shopping-offers/
https://leetcode.com/problems/2-keys-keyboard/
https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
https://leetcode.com/problems/best-team-with-no-conflicts/
https://leetcode.com/problems/profitable-schemes/
https://leetcode.com/problems/tallest-billboard/
https://leetcode.com/problems/pizza-with-3n-slices/
https://leetcode.com/problems/reducing-dishes/

3.Multi Dimensions DP
https://leetcode.com/problems/triangle/
https://leetcode.com/problems/combination-sum-iv/
https://leetcode.com/problems/out-of-boundary-paths/
https://leetcode.com/problems/knight-probability-in-chessboard/
https://leetcode.com/problems/champagne-tower/
https://leetcode.com/problems/largest-sum-of-averages/
https://leetcode.com/problems/minimum-falling-path-sum/
https://leetcode.com/problems/video-stitching/
https://leetcode.com/problems/longest-arithmetic-subsequence/
https://leetcode.com/problems/stone-game-ii/
https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
https://leetcode.com/problems/dice-roll-simulation/
https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
https://leetcode.com/problems/create-maximum-number/
https://leetcode.com/problems/frog-jump/
https://leetcode.com/problems/split-array-largest-sum/
https://leetcode.com/problems/freedom-trail/
https://leetcode.com/problems/minimum-number-of-refueling-stops/
https://leetcode.com/problems/number-of-music-playlists/
https://leetcode.com/problems/count-vowels-permutation/
https://leetcode.com/problems/minimum-falling-path-sum-ii/
https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/
https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/
https://leetcode.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons/
https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/
https://leetcode.com/problems/paint-house-iii/
https://leetcode.com/problems/count-all-possible-routes/

4.Interval DP
https://leetcode.com/problems/guess-number-higher-or-lower-ii/
https://leetcode.com/problems/arithmetic-slices/
https://leetcode.com/problems/predict-the-winner/
https://leetcode.com/problems/palindromic-substrings/
https://leetcode.com/problems/stone-game/
https://leetcode.com/problems/minimum-score-triangulation-of-polygon/
https://leetcode.com/problems/last-stone-weight-ii/
https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
https://leetcode.com/problems/stone-game-vii/
https://leetcode.com/problems/burst-balloons/
https://leetcode.com/problems/remove-boxes/
https://leetcode.com/problems/strange-printer/
https://leetcode.com/problems/valid-permutations-for-di-sequence/
https://leetcode.com/problems/minimum-cost-to-merge-stones/
https://leetcode.com/problems/allocate-mailboxes/
https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
https://leetcode.com/problems/stone-game-v/

5.bit DP
https://leetcode.com/problems/can-i-win/
https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
https://leetcode.com/problems/stickers-to-spell-word/
https://leetcode.com/problems/shortest-path-visiting-all-nodes/
https://leetcode.com/problems/smallest-sufficient-team/
https://leetcode.com/problems/maximum-students-taking-exam/
https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/
https://leetcode.com/problems/minimum-cost-to-connect-two-groups-of-points/
https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests/
https://leetcode.com/problems/distribute-repeating-integers/
https://leetcode.com/problems/maximize-grid-happiness/
https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/

6.Digit DP
https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/
https://leetcode.com/problems/numbers-at-most-n-given-digit-set/
https://leetcode.com/problems/numbers-with-repeated-digits/

7.DP on Trees
https://leetcode.com/problems/unique-binary-search-trees-ii/
https://leetcode.com/problems/house-robber-iii/
https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
https://leetcode.com/problems/linked-list-in-binary-tree/
https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
https://leetcode.com/problems/binary-tree-cameras/
https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/

8.String DP
https://leetcode.com/problems/is-subsequence/
https://leetcode.com/problems/palindrome-partitioning/
https://leetcode.com/problems/palindrome-partitioning-ii/
https://leetcode.com/problems/word-break/
https://leetcode.com/problems/unique-substrings-in-wraparound-string/
https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
https://leetcode.com/problems/longest-string-chain/
https://leetcode.com/problems/longest-happy-string/
https://leetcode.com/problems/longest-valid-parentheses/
https://leetcode.com/problems/distinct-subsequences/
https://leetcode.com/problems/word-break-ii/
https://leetcode.com/problems/count-the-repetitions/
https://leetcode.com/problems/concatenated-words/
https://leetcode.com/problems/count-different-palindromic-subsequences/
https://leetcode.com/problems/distinct-subsequences-ii/
https://leetcode.com/problems/longest-chunked-palindrome-decomposition/
https://leetcode.com/problems/palindrome-partitioning-iii/
https://leetcode.com/problems/find-all-good-strings/
https://leetcode.com/problems/string-compression-ii/
https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/

9.Probability DP
https://leetcode.com/problems/soup-servings/
https://leetcode.com/problems/new-21-game/
https://leetcode.com/problems/airplane-seat-assignment-probability/

10.Classic DPs
A.Cadane's Algorithm
https://leetcode.com/problems/maximum-subarray/
https://leetcode.com/problems/maximum-product-subarray/
https://leetcode.com/problems/bitwise-ors-of-subarrays/
https://leetcode.com/problems/longest-turbulent-subarray/
https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/
https://leetcode.com/problems/k-concatenation-maximum-sum/
https://leetcode.com/problems/largest-divisible-subset/
https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/

B.LCS
https://leetcode.com/problems/longest-palindromic-substring/
https://leetcode.com/problems/longest-palindromic-subsequence/
https://leetcode.com/problems/maximum-length-of-repeated-subarray/
https://leetcode.com/problems/longest-common-subsequence/
https://leetcode.com/problems/regular-expression-matching/
https://leetcode.com/problems/wildcard-matching/
https://leetcode.com/problems/edit-distance/
https://leetcode.com/problems/interleaving-string/
https://leetcode.com/problems/shortest-common-supersequence/
https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
https://leetcode.com/problems/max-dot-product-of-two-subsequences/

C.LIS
https://leetcode.com/problems/longest-increasing-subsequence/
https://leetcode.com/problems/number-of-longest-increasing-subsequence/
https://leetcode.com/problems/russian-doll-envelopes/
https://leetcode.com/problems/delete-columns-to-make-sorted-iii/
https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
https://leetcode.com/problems/maximum-height-by-stacking-cuboids/
https://leetcode.com/problems/make-array-strictly-increasing/

D.2D Grid Traversal
https://leetcode.com/problems/unique-paths/
https://leetcode.com/problems/unique-paths-ii/
https://leetcode.com/problems/minimum-path-sum/
https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/
https://leetcode.com/problems/where-will-the-ball-fall/
https://leetcode.com/problems/dungeon-game/
https://leetcode.com/problems/cherry-pickup/
https://leetcode.com/problems/number-of-paths-with-max-score/
https://leetcode.com/problems/cherry-pickup-ii/
https://leetcode.com/problems/kth-smallest-instructions/

E.Cumulative Sum
https://leetcode.com/problems/range-sum-query-immutable/
https://leetcode.com/problems/maximal-square/
https://leetcode.com/problems/range-sum-query-2d-immutable/
https://leetcode.com/problems/largest-plus-sign/
https://leetcode.com/problems/push-dominoes/
https://leetcode.com/problems/largest-1-bordered-square/
https://leetcode.com/problems/count-square-submatrices-with-all-ones/
https://leetcode.com/problems/matrix-block-sum/
https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
https://leetcode.com/problems/count-submatrices-with-all-ones/
https://leetcode.com/problems/ways-to-make-a-fair-array/
https://leetcode.com/problems/maximal-rectangle/
https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
https://leetcode.com/problems/super-washing-machines/
https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
https://leetcode.com/problems/get-the-maximum-score/

F.Hashmap (SubArray)
https://leetcode.com/problems/continuous-subarray-sum/
https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/

11.DP + Alpha (Tricks/DS)
https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
https://leetcode.com/problems/odd-even-jump/
https://leetcode.com/problems/constrained-subsequence-sum/
https://leetcode.com/problems/delivering-boxes-from-storage-to-ports/

12.Insertion DP
https://leetcode.com/problems/k-inverse-pairs-array/

13.Graph DP
https://leetcode.com/problems/cheapest-flights-within-k-stops/
https://leetcode.com/problems/find-the-shortest-superstring/

14.Memoization
https://leetcode.com/problems/minimum-jumps-to-reach-home/
https://leetcode.com/problems/scramble-string/
https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/
https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/
https://leetcode.com/problems/jump-game-v/
https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/

15.Binary Lifting
https://leetcode.com/problems/kth-ancestor-of-a-tree-node/

16.Math
https://leetcode.com/problems/ugly-number-ii/
https://leetcode.com/problems/count-sorted-vowel-strings/
https://leetcode.com/problems/race-car/
https://leetcode.com/problems/super-egg-drop/
https://leetcode.com/problems/least-operators-to-express-number/
https://leetcode.com/problems/largest-multiple-of-three/
https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/

### Revision

1. [Coding Patterns: A Cheat Sheet](revision/Revision.md)


### Test Your Knowledge
1. [Easy Problems](test-your-knowledge/1.EasyProblems.md)
2. [Medium Problems](test-your-knowledge/2.MediumProblems.md)
3. [Hard Problems](test-your-knowledge/3.HardProblems.md)



