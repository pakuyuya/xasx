
 last build ![a](https://circleci.com/gh/pakuyuya/xasx.png?circle-token=32161a51b19b1abd4b55346145f25a09f96c09bb) |

 * XMLファイルの検証を、同じくXMLで書かれたvalidation定義で検証で行うシンプルなライブラリ。
 * Java 8+

 * XMLファイルの検証で、例えば以下２つの```<ids>```みたいに、属性値が違うものには全く別のチェックを行うことがよくある。
```
<books>
  <book>
    <ids id-type="type-A">111-2223456</ids>
    <ids id-type="type-B">DIC:abcd:efgX</ids>
  </book>
</books>
```
 * 実際は十、数十倍のルールがあり、いちいち if で書いていくとひどいことになるのは目に見えている。
 * １つのタグを見るだけで完結する単純な検証を、大事な大事なコードベースから分離させるのが目的。
 * このライブラリは、以下のようなコンフィグファイルで検証を行う。
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

 * 「type-Aが存在したらtype-Bは存在しない」みたいな、複数の項目が必要になるチェックは目的としてない。むしろ、その手は適切なモデルにparseしてから if - else で検証するほうが都合が良いのでは？


## コンフィグファイル

* コンフィグファイル例
```
<?xml version="1.0" encoding="UTF-8" ?>
<rulefile>
  <settings>
    <prop name="rule-prefix">rule:</prop>
    <rules>
      <rule name="regex" class="org.ppa.xmlvalidator.preset.RegexRule" />
      <rule name="repeat" class="org.ppa.xmlvalidator.preset.RepeatRule" />
    </rule>
  </settings>
  <validation>
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
    </book>
  </books>
  </validation>
</rulefile>
```

 * ```<include>``` 機能や別途コンフィグファイルのロード機能も取り込む予定。

## マッチングルール

 * ```<validation>```内での検証定義は、タグ名・属性名の完全一致で判断する。
 * 例えば以下の場合では、「```<ids>```タグすべて」「```<ids>```タグで 属性```id-type="type-A"```のタグすべて」「```<ids>```タグで ```id-type="type-B"```のタグすべて」の３つの検証ルールとなる。
```
    <ids>
      <rule:repeat max="1" />
    </ids>
    <ids id-type="type-A">
      <rule:regex  pattern="^\d{3}-\d{7}$" trim="ASCII" />
    </ids>
    <ids id-type="type-B">
      <rule:regex  pattern="^DIC:[a-z]{4}:[a-z]{3}X$" trim="ASCII" />
    </ids>
```
 * ワイルドカードや正規表現指定は、今のところ予定していない。（core変えて対応するかも）

## ルールの追加

 * ```Rule```クラスを```implement```し、検証ルールを任意に追加できる。
 * コンフィグファイル内 ```<rules>``` にエントリーを追加することで対応可能。
 * DIやゼロコンフィグは未定。
