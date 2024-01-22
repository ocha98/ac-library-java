# AC Library Java 導入方法

## 導入方法
GitHubのリポジトリをクローンするか、[リリースページ](https://github.com/ocha98/ac-library-java/releases)からソースをダウンロードしてください。

ディレクトリ内にある`builder.sh`を実行し、`ac_library.jar`を作成してください。

windowsなどでスクリプトが実行できない場合は、`bulder.sh`の中に書いてあるコマンドを手動で実行してください。

`src/bin`内にある`ac_library.jar`をお好きな場所に保存してください。

コンパイル時は、`-cp`で`ac_library.jar`のパスを指定し`javac`を実行してください。

`<path_to_ac_library.jar>`は適宜置き換えてください。

```bash
javac -cp <path_to_ac_library.jar>/ac_library.jar Main.java
```

実行時も同様に指定してください。`ac_library.jar`の後ろに`:`がついていることに注意してください。

```bash
java -cp <path_to_ac_library.jar>/ac_library.jar: Main
```

## VSCode
`.vscode/setting.json`で以下を追記することで候補が出るようになります。

```json
"java.project.referencedLibraries": [
    "<path_to_ac_library.jar>/ac_library.jar"
]
```

## Eclipse
1. プロジェクト右クリック
2. Build Path → Add External Archives to Java Build Path
3. 保存した場所へ行き、`ac_library.jar`を選択し追加

