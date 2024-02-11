# クラス ContestScanner

競技プログラミングで利用できる入力クラスです。
`java.util.Scanner`等の一般的な入力クラスと比較して高速ですが、ASCII文字以外を入力した際に意図しない動作が発生することがあるので注意してください。

## コンストラクタ

```java
public ContestScanner(java.io.InputStream in) // (1)
public ContestScanner(java.io.File file,) // (2)
public ContestScanner() // (3)
```
指定された入力ストリームからContestScannerを構築します。

（３）は（１）にSystem.inを渡したものと同等です。

## メソッド

### hasNext
```java
public boolean hasNext()
```
このスキャナが入力内に別のトークンを保持する場合はtrueを返します。

### next
```java
public String next()
```
このスキャナから次の完全なトークンを検索して返します。

次トークンが無かった場合`NoSuchElementException`が発生します。

### 数値入力
```java
public int nextInt()
public long nextLong()
public double nextDouble()
```
次のトークンを`int`型/`long`型/`double`型として解釈して返します。

次トークンが無かった場合`NoSuchElementException`が発生します。

不正な文字やオーバーフローが発生する場合`NumberFormatException`が発生します。

```java
public int nextInt1()
public long nextLong1()
```
入力を受け取る際、自動で１デクリメントします。

### 配列入力
```java
public long[] nextLongArray(int length)
public int[] nextIntArray(int length)
public double[] nextDoubleArray(int length)
```
`length`個の連続した値を読み取り, 配列として返します。

```java
public long[] nextLongArray1(int length)
public int[] nextIntArray1(int length)
```
入力を受け取る際、すべての要素を１デクリメントします。

```java
public long[] nextLongArray(int length, java.util.function.LongUnaryOperator map)
public int[] nextIntArray(int length, java.util.function.IntUnaryOperator map)
public double[] nextDoubleArray(int length, java.util.function.DoubleUnaryOperator map)
```
`length`個の連続した値を読み取り、各要素に関数`map`を適用したものを配列として返します。
1-indexで与えられた入力を0-indexに変換するなどの用途を想定しています。

### 二次元配列入力
```java
public long[][] nextLongMatrix(int height, int width)
public int[][] nextIntMatrix(int height, int width)
public double[][] nextDoubleMatrix(int height, int width)
```
H行W列に並んだ値を読み取り、二次元配列として返します。

```java
public long[][] nextLongMatrix1(int height, int width)
public int[][] nextIntMatrix1(int height, int width)
```
入力を受け取る際、すべての要素を１デクリメントします。

```java
public char[][] nextCharMatrix(int height, int width)
```
H行W列に並んだ文字を読み取り、二次元配列として返します。
前述の数値型に関するメソッドと異なり、各行での空白区切りはないものとしていることに注意してください。

