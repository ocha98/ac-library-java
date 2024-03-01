# クラス StaticRollingHash
- - -

このクラスは有志による AtCoder Library 外のライブラリです。  
内容に関する質問等は viral ([@_viral8code](https://twitter.com/_viral8code/)) にお願いします。

文字列に対してランダムに決められた底を用いてハッシュ値を計算します。  
ハッシュ値は $\mod 2^{61}-1$ で計算しております。詳しくは以下のリンクをご参照下さい。  
[安全で爆速なRollingHashの話](https://qiita.com/keymoon/items/11fac5627672a6d6a9f6)  


## コンストラクタ
### StaticRollingHash
```java
public StaticRollingHash(String str)
```
指定された文字列を元にハッシュ値を計算します。

**計算量**
* $O( \mathrm{str.length}() )$

```java
public StaticRollingHash(StaticRollingHash rh)
```
`rh` を複製します。

**計算量**
* $O( \mathrm{rh.length}() )$

## メソッド
### getHash
```java
public long getHash(int l, int r)
```
連続部分文字列 $[l,r)$ のハッシュ値を返します。

**制約**
* $0 \leq l \leq r \leq \mathrm{length}()$

**計算量**
* $O(1)$

### equals
```java
public boolean equals(StaticRollingHash rh, int l1, int r1, int l2, int r2)
public boolean equals(String str, int l1, int r1, int l2, int r2)
```
自身の連続部分文字列 $[l_1,r_1)$ と `rh` の連続部分文字列 $[l_2,r_2)$ のハッシュ値が等しいか返します。

**制約**
* $0 \leq l_1 \leq r_1 \leq \mathrm{length}()$
* $0 \leq l_2 \leq r_2 \leq \mathrm{rh.length}()$

**計算量**
* $O(1)$ (`StaticRollingHash`)
* $O(r_1-l_1)$ (`String`)

### length
```java
public int length()
```
この `StaticRollingHash` の元となる `String` の長さを返します。

**計算量**
* $O(1)$

### hashCode
```java
public int hashCode()
```
この `StaticRollingHash` の元となる `String` のハッシュ値を返します。

**計算量**
* $O(1)$

### toString
```java
public String toString()
```
この `StaticRollingHash` の元となる `String` を返します。

**計算量**
* $O(1)$

### equals
```java
public boolean equals(Object o)
```
この `StaticRollingHash` と等価な `StaticRollingHash` か返します。

**計算量**
* $O(1)$

### compareTo
```java
public int compareTo(StaticRollingHash rh)
public int compareTo(String str)
```
`String` の `compareTo` メソッドに従って文字列を比較します。

**計算量**
* $O(\mathrm{length}())$

### charAt
```java
public char charAt(int i)
```
この `StaticRollingHash` の $i$ 文字目を表す `char` を返します。

**計算量**
* $O(1)$

### compareToIgnoreCase
```java
public int compareToIgnoreCase(StaticRollingHash rh)
public int compareToIgnoreCase(String str)
```
`String` の `compareToIgnoreCase` メソッドに従って文字列を比較します。

**計算量**
* $O(\mathrm{length}())$

### contains
```java
public boolean contains(StaticRollingHash rh)
public boolean contains(String str)
```
`StaticRollingHash` は ハッシュ値を、`String` は `String` の `contains` メソッドに従って文字列を探索します。

**計算量**
* $O(\mathrm{length}())$

### indexOf
```java
public int indexOf(int ch)
public int indexOf(int ch, int fromIndex)
public int indexOf(String str)
public int indexOf(String str,int fromIndex)
```
`char` は線形探索、`String` は一度ハッシュ値を計算してから線形探索を行ないます。

**計算量**
* $O(\mathrm{length}())$

### isEmpty
```java
public boolean isEmpty()
```
この `StaticRollingHash` が空文字列を表しているか返します。

**計算量**
* $O(1)$

### lastIndexOf
```java
public int lastIndexOf(int ch, int fromIndex)
public int lastIndexOf(int ch)
```
この `StaticRollingHash` が空文字列を表しているか返します。

**計算量**
* $O(\mathrm{fromIndex})$

### valueOf
```java
public static StaticRollingHash valueOf(boolean b)
public static StaticRollingHash valueOf(char c)
public static StaticRollingHash valueOf(char[] c)
public static StaticRollingHash valueOf(char[] c,int offset,int count)
public static StaticRollingHash valueOf(double d)
public static StaticRollingHash valueOf(float f)
public static StaticRollingHash valueOf(int i)
public static StaticRollingHash valueOf(long l)
public static StaticRollingHash valueOf(Object obj)
```
`String` の `valueOf` メソッドによって生成された文字列を元に `StaticRollingHash` を生成します。

**計算量**
* `String::valueof` で生成される文字列を $S$ としたとき、 $O(S\mathrm{.length}())$
