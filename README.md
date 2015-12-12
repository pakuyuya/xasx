 * XMLファイルの検証を、同じくXMLで書かれたvalidation定義で検証で行うライブラリ。
 * Java 8+

 * 例えば、仕事でXMLフォーマットの検証を実装することになった。どうやら、以下の```<ids>```タグは全くの別物のようだ。
```
<books>
  <book>
    <ids id-type="type-A">111-2223456</ids>
    <ids id-type="type-B">DIC:abcd:efgX</ids>
  </book>
</books>
```
 * Javaのmodle検証な validator は、要素名はちゃんと分けてくれるんだけど、属性値によってケースを分ける場合は、実装者の負担になる。そして、だいたい保守性の低い if else のミートソースもりもりになってしまう！！

 * じゃ、こんなルールファイルがあったらどうかな。

```
<?xml version="1.0" encoding="UTF-8" ?>
<rulefile>
  <validation>
  <books>
    <book>
      <ids id-type="type-A">
        <rule:regex  pattern="^\d{3}-\d{7}$" trim="ASCII" />
        <rule:repeat max="1" />
      </ids>
      <ids id-type="type-B">
        <rule:regex  pattern="^DIC:[a-z]{4}:[a-z]{3}X$" trim="ASCII" />
        <rule:repeat max="1" />
      </ids>
    </book>
  </books>
  </validation>
</rulefile>
```

 * ・・・多少言いたいことはあるんだろうけど、```if```でこの程度のことを実装するよりはだいぶだいぶ見やすいじゃないかな。

 * 「type-Aが存在したらtype-Bは存在しない」みたいな、複数の項目が必要になるチェックは期待しないでほしい。（本当に、その場でベタに```if```を書く場合が適していることもある。）
 * ただ、このライブラリは以下みたいな解法は用意するつもりだ。

```
<?xml version="1.0" encoding="UTF-8" ?>
<rulefile>
  <settings>
  </settings>
  <validates>
  <books>
  <book>
    <ids>
      <rule:repeat max="1" />
    </ids>
    <ids id-type="type-A">
      <rule:regex  pattern="^\d{3}-\d{7}$" trim="ASCII" />
    </ids>
    <ids id-type="type-B">
      <rule:regex  pattern="^DIC:[a-z]{4}:[a-z]{3}X$" trim="ASCII" />
    </ids>
  </boks>
  </validates>
</rulefile>
```