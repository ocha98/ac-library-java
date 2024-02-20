# Embedder
---

`embedder.py` は１つのソースファイルに `ac_library.jar` をバイナリで埋め込むスクリプトです。

## 使い方
以下のコマンドを実行すると埋め込まれた `Out.java` が生成されます。

```bash
$ python embedder.py Main.java
```

## オプション
- `-c`: 埋め込まれたコードを標準出力に出力します。
- `-o`: 保存パスを指定します。デフォルトは `Out.java` です。
- `-l`: `ac_library.jar` の場所を指定します。デフォルトは `ac_library.jar` です。 
- `-t`: コンパイラのバージョンを指定します。デフォルトは１７です。

`-t` で指定するバージョンは `ac_library.jar` のターゲットバージョン以上でなければなりません。