# UCI-MSWE

[![HitCount](http://hits.dwyl.com/joey66666/UCI-MSWE.svg)](http://hits.dwyl.com/joey66666/UCI-MSWE)

![image](https://user-images.githubusercontent.com/25404074/100097926-368c4080-2e98-11eb-8ccd-26915fcd4711.png)

Quarter Fall 2020 Course Projects for Master of Software Engineering at University of California Irvine.

- [SWE 241P Applied Data Structure and Algorithms](#uci-swe-241p-applied-data-structure-and-algorithms)
- [SWE 242P Network Programming](#uci-swe-242p-network-programming)
- [SWE 243P Database Programming](#uci-swe-243p-database-programming)
- [SWE 244P Concurrent Programming](#uci-swe-244p-concurrent-programming)
- [SWE 246P Mobile Programming - iOS](https://github.com/joey66666/QuizAPP)
- [SWE 250P Web Programming](https://github.com/Apress/modern-full-stack-development)

**Books are [here](https://github.com/joey66666/UCI-MSWE/tree/main/Books).**

## UCI SWE 241P Applied Data Structure and Algorithms

### Exercise 1
- Implement three sets based on Linked list, Binary tree, Hash table.
- Read and tokenize an input text file and store in sets, search words in each set.
- Measure how much time (in nanoseconds) it takes to insert and search in the sets.

### Exercise 2
- Implement the Selection sort, Insertion sort, Heapsort, Mergesort, Quicksort.
- Read and tokenize an input text file and sort using the above algorithms.
- Measure the relative performance of the algorithms, similar to Exercise 1.

### Exercise 3
- Convert from an adjacency matrix to adjacency lists.
- Convert from an adjacency list to an incidence matrix.
- Convert from an incidence matrix to adjacency lists.

### Exercise 4
- Implement both a breadth-first search algorithm and a depth-first search algorithm.
- Experiment the above algorithms on a graph.


## UCI SWE 242P Network Programming

### Exercise M2
- Count the number of lines in each file that is specified on the command line. 

### Exercise M3
- Network file server and client run on top of TCP. 
- Read commands from command-line and transfer file text.

### Exercise M4
- Revise network file server program from module 3 to work on top of the UDP protocol. 
  1. UDP datagram has a size limit; needs to break the large files into smaller chunks that can be transported using UDP.  
  2. UDP is unreliable; implement the additional logic on top of the UDP protocol to ensure files are transmitted reliably on top of this protocol. 


##  UCI SWE 243P Database Programming

### Exercise M5.1
- Keeps track of students, courses, and their schedules. 
  - Allow new students to enroll into the program
  - New courses to be introduced, students to enroll in courses
  - Querying to see which students are in each course
  - Querying to see which courses each student is in
  - Querying to see which courses and what times each course is for a given student on a given day of the week.
- Interact with GUI interface.
![Ex M5.1](https://raw.githubusercontent.com/joey66666/UCI-MSWE/main/243P/Ex/Ex%20M5.1/Demo.png)

##### Dependencies
- Tkinter
- Pymysql

## UCI SWE 244P Concurrent Programming

Course Project for **SWE 244P Concurrent Programming**.

### Exercise 1
Multi-threaded Hello World
- a - Create a new thread.
- b - Stop a given thread (e.g. "b 2" kills thread 2).
- c - Stop all threads and exit this program".

### Exercise 2.1
- Make JDisplay2 thread safe.

### Exercise 2.2
- Add synchronization so that the cars don't collide.

### Exercise 3.1
- Make HashMapTest thread safe.

### Exercise 3.2
- Make VectorTest thread safe.

### Exercise 3.3
- Complete the missing pieces. The program should behave correctly, in that no messages should be lost. Make sure that the sum of all sent messages equals the sum of all received messages.
- Change the main method so that two consumer threads are created, both of them consuming messages from the single queue. Your program should stop gracefully, as in 1.
- Change the main method so that, additionally to the two consumer threads of the previous point, two producer threads are created, both of them sending messages to the single queue.
- Finally, change the main method so that N consumers and M producers are created. N and M should be given as command line arguments to the program.

### Exercise 3.4
- Solve the synchronization problems with a semaphore outside JDisplay2.

### Exercise 3.5
- Make the program work correctly, avoiding all race conditions and all deadlocks.

### Exercise 4.1
- Make the Web server multi-threaded, so it can handle multiple requests at the same time.

### Exercise 4.2
- Make this term frequency program multi-threaded, so it can process multiple text files at the same time.

### Exercise 5
- Reimplenent Exercise 4.2 in Python.
- Read all text files in the directory.
- Pocess the counting of words in each text file in a separate thread.
## UCI SWE 250P Web Programming

### [Apress/Modern Full Stack Programming](https://github.com/Apress/modern-full-stack-development)

## UCI SWE 246P Mobile Programming - iOS

### [QuizAPP](https://github.com/joey66666/QuizAPP)

## Acknowledgement

**Hopefully the code could help you, but according to the rubric, copy-and-paste is not allowed.**