# クラス ContestPrinter
- - -

競技プログラミングで利用できる出力クラスです。

⚠️注意⚠️：最後に `close()` か `flush()` を呼び出すことを忘れないでください。

## コンストラクタ
```java
public ContestPrinter(java.io.OutputStream out)
public ContestPrinter()
```
指定された出力ストリームから新しい `ContestPrinter` を作成します。 指定を省略した場合は `System.out` が指定されます。

## メソッド
### print
```java
public final void print(type x)
```
`String` を含む全てのプリミティブ型に対応した `print` メゾットです。

`double` 、 `float` 型の出力では小数点以下 $20$ 桁まで通常の形式での出力を行います。

### println
```java
public final void println(type x)
```
`String` を含む全てのプリミティブ型に対応した `println` メゾットです。

`print` メゾットと異なるのは最後に改行を出力します。

### printlnArray
```java
public void printlnArray(int[] array)
public void printlnArray(int[] array, String separator)
public void printlnArray(int[] array, java.util.function.IntUnaryOperator map)
public void printlnArray(int[] array, String separator, java.util.function.IntUnaryOperator map)

public void printlnArray(long[] array)
public void printlnArray(long[] array, String separator)
public void printlnArray(long[] array, java.util.function.LongUnaryOperator map)
public void printlnArray(long[] array, String separator, java.util.function.LongUnaryOperator map)

public <T> void printlnArray(T[] array)
public <T> void printlnArray(T[] array, String separator)
public <T> void printlnArray(T[] array, java.util.function.UnaryOperator<T> map)
public <T> void printlnArray(T[] array, String separator, java.util.function.UnaryOperator<T> map)
```
`array` の各要素に対して関数 `map` を適用し、 `separator` 区切りで出力します。行末には `separator` は含まれず、改行が挿入されます。
`map` 及び `separator` は省略可能で、省略した場合は恒等写像 / 半角スペースが与えられた場合と同等の出力になります。

`map` は $0$ -indexed の計算結果を $1$ -indexed に変換するような場合に利用するとよいです。
