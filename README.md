# UCI-MSWE

<a title="Hits" target="_blank" href="https://github.com/joey66666/uci-mswe"><img src="https://hits.b3log.org/joey66666/uci-mswe.svg"></a>

![image](https://user-images.githubusercontent.com/25404074/100097926-368c4080-2e98-11eb-8ccd-26915fcd4711.png)

Quarter Fall 2020 Course Projects for Master of Software Engineering at University of California Irvine.

- [SWE 241P Applied Data Structure and Algorithms](#uci-swe-241p-applied-data-structure-and-algorithms)
- [SWE 242P Network Programming](#uci-swe-242p-network-programming)
- [SWE 243P Database Programming](#uci-swe-243p-database-programming)
- [SWE 244P Concurrent Programming](#uci-swe-244p-concurrent-programming)
- [SWE 246P Mobile Programming (iOS)](https://github.com/joey66666/QuizAPP)
- [SWE 250P Web Programming](#uci-swe-250p-web-programming)

**Books are [here](https://github.com/joey66666/UCI-MSWE/tree/main/Books).**

### According to the rubric, copy-and-paste is not allowed.

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

## UCI SWE 246P Mobile Programming (iOS)

### Implementation of 246P is here: [QuizAPP](https://github.com/joey66666/QuizAPP)

## UCI SWE 250P Web Programming

### Original Repo: [Apress/Modern Full Stack Programming](https://github.com/Apress/modern-full-stack-development)
### Practice M1
- Read, reproduce, execute, and understand codes from the HTML&CSS Design and Build Websites textbook. Modify the example code from Chapter 17 in order for you to create a simple webpage of your own - you can think about using this later as your webpage! The aim is for you to get acquainted with HTML/CSS to a point that you will feel comfortable creating simple webpages from scratch.

### Practice M2
- Develop your first web server using Node.js and NPM, learn how to work with these important tools of the modern web stack, have your first web server up and running. Use the web server that you created locally to serve a very simple, single-page webpage, and describe and report what you have done. The aim is for you to start understanding how a simple web server works and get acquainted with Node.js so that you will be able to use it in your future work and during this course.
- Using the relevant partial codes from Chapters 1 and 2 of the textbook, create codes by your own that will allow you to have a simple web server up and running and serving the webpage that you created in the first assignment.

### Practice M3
- Add some React components to your webpage based on the codes from Chapters 3 and 4 of the textbook. The aim is for you to get acquainted with React and how React can be used to improve significantly the user experience in more complex web applications - as you will be developing later in this course.
- Using the relevant partial codes from Chapter 3 and 4 of the textbook, either modify the web page that you developed in the first assignment so you can add React components or create a new web page from scratch to demonstrate your React understanding.

### Practice M4
- Read, reproduce line-by-line, execute, and understand the code from Chapter 5 of the textbook. The aim is for you to learn the basics of TypeScript, which is an important modern language for web development that is becoming each time more popular in the web industry.
- From Chapter 5 of the textbook, make sure to reproduce all the codes. Create a small webpage or modify the webpage that you have been developing throughout this course to demonstrate that you understand how to write simple TypeScript code. Your webpage can be about anything, as long as it is fully functional and that it includes TypeScript elements. Make sure to use arrow functions and some classes also.

### Practice M5
- Read, reproduce line-by-line, execute, and understand the code from Chapter 6 and 7 of the textbook. The aim is for you to learn more advanced features of TypeScript and learn how to create bundles that simplify the management of the development and deployment of your web applications.
- From Chapter 6 of the textbook, make sure to reproduce all the codes. Add some mode advanced features of TypeScript to the webpage that you have been working in the previous assignment. You should at least demonstrate that you can work with modules, but if you wish you can also start using third party libraries.

### Practice M6
- Read, reproduce line-by-line, execute, and understand the code of Chapter 8 of the textbook. The aim here is for you to merge together different tools and techniques that you have seen in the previous assignments and readings to build a fully functional web application, in this case, the server-side component of your own webmail system!
- From Chapter 8 of the textbook, make sure to reproduce all the codes and modify them at will adding whatever exclusive features you feel that would improve your webmail system.

### Practice M7
- Read, reproduce line-by-line, execute, and understand the code of Chapter 9 of the textbook. The aim here is for you to merge together different tools and techniques that you have seen in the previous assignments and readings to build a fully functional web application, in this case, the client-side component of your own webmail system! You can be creative here and if you wish you can modify and write a new code to provide a different user experience for email than it is provided by normal webmail systems! Who knows, this can even become a kickoff idea of your own startup!
- From Chapter 9 of the textbook, make sure to reproduce all the codes and modify them at will adding whatever exclusive features you feel that would improve your webmail system. Make sure to profit from the knowledge that you acquired since the first module of this course to improve the user experience of your web application.

## Acknowledgement

**Hopefully the code could help you, but according to the rubric, copy-and-paste is not allowed.**
