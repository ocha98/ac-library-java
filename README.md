# AC Library Java

Forked from [NASU41/AtCoderLibraryForJava](https://github.com/NASU41/AtCoderLibraryForJava)

Web Document: [AC Library Java Document](https://ocha98.github.io/ac-library-java/)

## 使い方
### jar の入手
1. [リリースページ](https://github.com/ocha98/ac-library-java/releases)から jar をダウンロードします。ファイル名は `ac_library<java_release>.jar` です。ジャッジ等のバージョンに合ったものを選択してください。
2. 適合するバージョンが見つからない場合は、ソースコードが含まれている `ac_library.zip` をダウンロードし `builder.sh` を使用して `ac_library.jar` を生成してください。

`builder.sh` で以下のようにリリースのバージョンを指定し `ac_library.jar` を生成します。

```bash
$ ./builder.sh <java_release>
```

### コンパイルと実行
jar ファイルと一緒に以下のようにコンパイルしてください。

```
javac -cp <path_to>/ac_library.jar Main.java
```

実行時は以下のように実行してください。

```
java -cp <path_to>/ac_library.jar: Main
```

## embedder.py
ライブラリを１つのソースファイルに埋め込むツールです。ジャッジにライブラリが導入されていなくても提出できるようになります。

## コントリビュートについて
コントリビュートしたい場合は、以下のようにしてください：

- 何か提案や変更がある場合は、 GitHub で Issue を作成してください。
- 直接コードの変更を行いたい場合は、プルリクエストを作成し、簡単な説明を添えて送信してください。

## License
CC0

