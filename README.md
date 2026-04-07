# Java Binary Search Tree

A compact Java implementation of a binary search tree (BST) that stores key/value pairs and exposes core tree operations through a dedicated interface. The project focuses on fundamental data-structure behavior, including insertion, lookup, deletion, multiple traversal strategies, and structural validation helpers such as height, size, fullness, completeness, and perfection checks.

![Repo Size](https://img.shields.io/github/repo-size/drussell33/Java-Binary-Search-Tree)
![Last Commit](https://img.shields.io/github/last-commit/drussell33/Java-Binary-Search-Tree)
![Top Language](https://img.shields.io/github/languages/top/drussell33/Java-Binary-Search-Tree)

## Key Features

- Generic binary search tree implementation for comparable keys and associated values
- Public interface abstraction via `IBinarySearchTree`
- Recursive insertion based on key ordering
- Key-based removal with in-order predecessor replacement for nodes with two children
- Lookup support through `find`
- Depth-first traversals:
  - Pre-order
  - In-order
  - Post-order
- Breadth-first traversal using a queue
- Tree metrics and shape inspection:
  - `size()`
  - `getHeight()`
  - `isFull()`
  - `isPerfect()`
  - `isComplete()`
- Simple entry-point class for running or extending the project locally
- Separate test source path containing `BinarySearchTreeTest.java`

## Tech Stack

### Backend
- Java

### Frontend
- None

### Database
- None

### Tools / Services
- IntelliJ IDEA project metadata (`.idea/`, `BinarySearchTree.iml`)
- Git / GitHub for source control and hosting

## Architecture Overview

This repository is a single-process Java data-structure project rather than a multi-tier application. There is no frontend, API layer, persistence layer, or external service integration.

The core architecture is organized around three parts:

1. **Contract layer**: `IBinarySearchTree` defines the operations supported by the tree.
2. **Implementation layer**: `BinarySearchTree` contains the concrete logic, a private nested `Node` type, and the traversal and structural-analysis methods.
3. **Execution layer**: `Main` provides a minimal application entry point for local experimentation.

Data flows entirely in memory. Consumers provide a comparable key and a value, and the tree stores that pair inside internal nodes. Traversal methods accept a `Consumer`, allowing callers to inject custom behavior without changing the tree implementation. The code uses recursive methods for most tree operations and a queue-backed iterative approach for breadth-first traversal.

## Project Structure

```tree
Java-Binary-Search-Tree/
├── .idea/
├── src/
│   ├── edu/
│   │   └── wou/
│   │       └── algorithms/
│   │           ├── BinarySearchTree.java
│   │           ├── IBinarySearchTree.java
│   │           └── Main.java
│   └── test/
│       └── edu/
│           └── wou/
│               └── algorithms/
│                   └── BinarySearchTreeTest.java
├── .gitignore
├── BinarySearchTree.iml
└── README.md
```

### Important Files

- `src/edu/wou/algorithms/BinarySearchTree.java` - Concrete BST implementation with node management, traversal logic, and structure-checking methods
- `src/edu/wou/algorithms/IBinarySearchTree.java` - Interface that defines the public contract for tree operations
- `src/edu/wou/algorithms/Main.java` - Minimal Java entry point
- `src/test/edu/wou/algorithms/BinarySearchTreeTest.java` - Test source location reserved for validating BST behavior

## Getting Started

### Prerequisites

- Java JDK 8 or later
- An IDE or Java compiler such as:
  - IntelliJ IDEA
  - VS Code with Java extensions
  - Command-line `javac` / `java`

### Installation

```bash
git clone https://github.com/drussell33/Java-Binary-Search-Tree.git
cd Java-Binary-Search-Tree
```

There is no build tool configuration in the repository, so dependencies are limited to the Java standard library.

### Usage

Compile the source files:

```bash
javac -d out src/edu/wou/algorithms/*.java
```

Run the application entry point:

```bash
java -cp out edu.wou.algorithms.Main
```

To experiment with the tree, add usage code inside `Main.java`, for example:

```java
BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
tree.insert(10, "root");
tree.insert(5, "left");
tree.insert(15, "right");

tree.inOrderTraversal(System.out::println);
```

## Roadmap

- [x] Define a binary search tree interface
- [x] Implement node insertion
- [x] Implement key lookup
- [x] Implement node removal
- [x] Add pre-order traversal
- [x] Add in-order traversal
- [x] Add post-order traversal
- [x] Add breadth-first traversal
- [x] Add tree size calculation
- [x] Add tree height calculation
- [x] Add full/perfect/complete tree checks
- [ ] Add richer sample usage in `Main.java`
- [ ] Add complete automated test coverage and assertions for all public methods
- [ ] Add a build tool configuration such as Maven or Gradle
- [ ] Add CI validation for compilation and tests
- [ ] Improve documentation around expected input behavior and edge cases
- [ ] Add balancing support or self-balancing variants for advanced use cases

## Contributing

Contributions are welcome through the standard GitHub workflow:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Commit with clear, descriptive messages
5. Push your branch
6. Open a pull request

When contributing, keep changes focused, preserve the existing package structure, and include tests or example usage updates where appropriate.

## Screenshots / Demo

_No screenshots or demo assets are currently included in the repository._

## Contact

- GitHub: [drussell33](https://github.com/drussell33)
