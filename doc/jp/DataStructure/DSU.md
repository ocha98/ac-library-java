# クラス DSU
- - -

$n$ 頂点の無向グラフに対して、

* 辺の追加
* $2$ 頂点が連結かの判定

を $O(\alpha(n))$ 時間で処理することが出来ます。

また、内部的に各連結成分ごとに代表となる頂点を $1$ つ持っています。辺の追加により連結成分がマージされる時、新たな代表元は元の連結成分の代表元のうちどちらかになります。

## コンストラクタ
### DSU
```java
public DSU(int n)
```
$n$ 頂点 $0$ 辺の無向グラフを作ります。

**制約**
* 頂点数 $n$ の配列 $a_0, a_1, \dots, a_{n-1}$ を作ります
* 初期値はすべて `-1` です
( C++ 版だと $10^8$ まで可能と書いてありますが、Java だと厳しいかもしれません)

**計算量**
* $O(n)$

## メソッド
### merge
```java
int merge(int a, int b)
```
辺 $(a,b)$ を足します。
$a,b$ が連結だった場合はその代表元、非連結だった場合は新たな代表元を返します( index 外の数字を入れた場合は `-1` を返します)。

**制約**
* $0 \leq a < n$
* $0 \leq b < n$

**計算量**
* ならし $O(\alpha(n))$

### same
```java
boolean same(int a, int b)
```
頂点 $a,b$ が連結かどうかを返します。

**制約**
* $0 \leq a < n$
* $0 \leq b < n$

**計算量**
* ならし $O(\alpha(n))$

### leader
```java
int leader(int a)
```
頂点 $a$ の属する連結成分の代表元を返します。

**制約**
* $0 \leq a < n$

**計算量**
* ならし $O(\alpha(n))$

### size
```java
int size(int a)
```
頂点 $a$ の属する連結成分のサイズを返します。

**制約**
* $0 \leq a < n$

**計算量**
* ならし $O(\alpha(n))$

### groups
```java
ArrayList<ArrayList<Integer>> groups()
```
グラフを連結成分に分け、その情報を返します。  
返り値は「「一つの連結成分の頂点番号のリスト」のリスト」です。 (内側外側限らず) `ArrayList` 内でどの順番で頂点が格納されているかは未定義です。

**計算量**
* $O(n)$

## 使用例
AtCoder Library Practice Contest: A - Disjoint Set Union [提出コード](https://atcoder.jp/contests/practice2/submissions/52255775)

```java
import ac_library.*;

public class Main {
    public static void main(String[] args) {
        ContestScanner scan = new ContestScanner();
        ContestPrinter pl = new ContestPrinter();
        int n = scan.nextInt();
        int q = scan.nextInt();

        DSU dsu = new DSU(n);
        while(q-- > 0) {
            int t = scan.nextInt();
            int u = scan.nextInt();
            int v = scan.nextInt();

            if(t == 0) {
                dsu.merge(u, v);
            } else {
                if(dsu.same(u, v)) {
                    pl.println('1');
                } else {
                    pl.println('0');
                }
            }
        }

        pl.close();
    }
}

```
