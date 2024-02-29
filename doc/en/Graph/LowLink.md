# Class GraphBuilder
- - -

This class constructs an undirected edge based on the given undirected edge and searches for articulation points and bridges.  
All methods assume $0$-indexed.

## Constructor
```java
public LowLink(int n)
```
Generates an undirected graph with $n$ vertices and $0$ edges.

**Computational complexity**
* $O(n)$

## Methods
### addEdge
```java 
public void addEdge(int u, int v)
```
Add an undirected edge $u,v$.

**Constraints**
* $0 \leq u, v < n$

**Computational complexity**
* Leveling $O(1)$

### build
```java 
public void build()
```
Build the graph based on the undirected edges added.
Must be called before calling the `articulationPoints` and `bridges` methods.

**Computational complexity**
* Let $m$ be the number of added edges $O(n+m)$

### articulationPoints
```java
public boolean[] articulationPoints()
```
Returns a `boolean` array of length $n$. The $i$-th element indicates whether the $i$-th vertex is an articulation point.

**Computational complexity**
* Let $m$ be the number of added edges $O(n+m)$

### bridges
```java
public boolean[] bridges()
```
Returns a `boolean` array of length $n$.  The $i$-th element indicates whether the $i$-th edge is a bridge.

**Computational complexity**
* Let $m$ be the number of edges added $O(n+m)$
