import numpy as np

# Create a 5x2 matrix A with random integers
# We use np.random.randint to generate a matrix of integers
# 'low' and 'high' set the range of random integers, here -10 to 10
# 'size' determines the shape of the matrix, here 5 rows and 2 columns
np.random.seed(0)  # Setting a random seed for reproducibility
A = np.random.randint(low=-10, high=10, size=(5, 2))

# Create a 2x1 vector x with random integers
x = np.random.randint(low=-10, high=10, size=(2, 1))

# Create a 5x5 diagonal matrix B with '12345' on the diagonal
# np.diag() is used to create a diagonal matrix from a list
B = np.diag([1, 2, 3, 4, 5])

# Calculate Ax (Matrix-Vector multiplication)
Ax = np.dot(A, x)

# Calculate BA (Matrix-Matrix multiplication)
BA = np.dot(B, A)

# Define a function to format and print matrices neatly
def print_matrix(label, matrix):
    print(f"{label}:\n{np.array_str(matrix)}\n")

# Print the created matrices and results using the function
print_matrix("Matrix A (5x2)", A)
print_matrix("Vector x (2x1)", x)
print_matrix("Diagonal Matrix B (5x5)", B)
print_matrix("Product Ax", Ax)
print_matrix("Product BA", BA)


# Assuming matrices A, B, and BA are already defined as per previous problems
# If not, you need to define them here or load them from your previous code

# Calculate the rank of matrix A
# np.linalg.matrix_rank is used to compute the rank of a matrix
rank_A = np.linalg.matrix_rank(A)

# Calculate the rank of matrix B
# For diagonal matrix B, its rank is equal to the number of non-zero diagonal elements
rank_B = np.linalg.matrix_rank(B)

# Calculate the rank of matrix BA
# The rank of the product of two matrices can be different from the individual ranks
rank_BA = np.linalg.matrix_rank(BA)

# Print the ranks
print("Rank of Matrix A:", rank_A)
print("Rank of Matrix B:", rank_B)
print("Rank of Matrix BA:", rank_BA)
