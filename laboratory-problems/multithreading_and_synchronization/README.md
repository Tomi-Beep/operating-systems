Grades from students across all faculties in Macedonia are given. The grades are placed in an array of 1,000,000 elements. It is required to implement the calculation of the average grade. Due to the size and in order to speed up the computation, the implementation should be done using multiple threads. Below is a sequential solution to the problem. Modify the solution according to the task requirements.

Define all necessary synchronization elements and initialize them in the init() method.
You are allowed to define other global and local variables if needed during the computation.

Given the large size of the array, the search optimization should be parallelized using multiple threads. The code should be modified so that 10 threads are started, and each of them processes a subarray of equal size. At the end, the average grade of students from all faculties (the variable average) should be printed. Implement the function calculateAverageGradeParallel. The function for parallel computation should not return a result. Passing the result back to the main thread should be done in another way.

Note: Do not change the code where it is marked with DO NOT CHANGE. Make sure to correctly start and stop the threads, and handle any synchronization if needed.
