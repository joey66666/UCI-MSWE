# UCI-MSWE
Quarter Fall 2020 Course Resources for Master of Software Engineering at University of California Irvine.

**Book Resources are [here](https://github.com/joey66666/UCI-MSWE/tree/main/Books).**

## UCI SWE241P

Course Project for **SWE 241P Applied Data Structure and Algorithms**.

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

## UCI SWE242P

Course Project for **SWE 242P Network Programming**.

### Exercise M2
- Count the number of lines in each file that is specified on the command line. 

### Exercise M3
- Network file server and client run on top of TCP. 
- Read commands from command-line and transfer file text.

### Exercise M4
- Revise network file server program from module 3 to work on top of the UDP protocol. 
  1. UDP datagram has a size limit; needs to break the large files into smaller chunks that can be transported using UDP.  
  2. UDP is unreliable; implement the additional logic on top of the UDP protocol to ensure files are transmitted reliably on top of this protocol. 

##  UCI SWE243P

Course Project for **SWE 243P Database Programming**.

### Exercise M5.1

- Keeps track of students, courses, and their schedules. 
  - Allow new students to enroll into the program
  - New courses to be introduced, students to enroll in courses
  - Querying to see which students are in each course
  - Querying to see which courses each student is in
  - Querying to see which courses and what times each course is for a given student on a given day of the week.
- Interact with GUI interface.
- ![Ex M5.1]()

##### Dependencies

- Tkinter
- Pymysql

### Acknowledgement

- **Hopefully the code could help you understand, but according to the rubric, copy-and-paste is not encouraged.**
- Thanks for students, teaching assistants, and professors at [Donald Bren School of Information and Computer Sciences, University of California, Irvine](https://www.ics.uci.edu/)


