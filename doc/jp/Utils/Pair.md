# クラス Permutation
- - -

本機能は AtCoderLibrary ではなく C++ 標準ライブラリ `std::pair` の移植です。
2要素の組を表現するオブジェクト型です。

## コンストラクタ

### Pair\<S extends Comparable\<S>, T extends Comparable\<T>>

```java
public Pair(S s, T t)
```
2要素 $s, t$ の組を作成します。

## メソッド

### equals

```java
public boolean equals(Object another)
```
この組がオブジェクト `another` と等しい時に `true` を返す関数です。
ここで、 Pair どうしが等しいとは、第一要素どうしが等しくかつ第二要素どうしが等しいことを指します。

**計算量**
* 第一要素の `equals` メソッドの計算量を $f$ 、第二要素の `equals` メソッドの計算量を $g$ としたとき、 $O(f+g)$

### compareTo

```java
public int compareTo(Pair<S,T> another)
```
このオブジェクトと指定されたオブジェクトの順序を比較します。
第一要素どうしが等しくない場合はその順序を、 等しい場合は第二要素どうしの順序を返します(いわゆる辞書順)。

**計算量**
* 第一要素の `compareTo` メソッドの計算量を $f$ 、第二要素の `compareTo` メソッドの計算量を $g$ としたとき、 $O(f+g)$
