# AC Library Java 導入方法

## 使い方
### jar の入手
1. [リリースページ](https://github.com/ocha98/ac-library-java/releases)から jar をダウンロードします。ファイル名は `ac_library<java_release>.jar` です。ジャッジ等のバージョンに合ったものを選択してください。
2. 適合するバージョンが見つからない場合は、ソースコードが含まれている `ac_library.zip` をダウンロードし `builder.sh` を使用し `ac_library.jar` を生成してください。

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

## VSCode
`.vscode/setting.json` で以下を追記することで候補が出るようになります。

```json
"java.project.referencedLibraries": [
    "<path_to_ac_library.jar>/ac_library.jar"
]
```

## Eclipse
1. プロジェクト右クリック
2. Build Path → Add External Archives to Java Build Path
3. 保存した場所へ行き、 `ac_library.jar` を選択し追加

