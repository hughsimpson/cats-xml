# XPath support

Add XPath support.

```sbt
libraryDependencies += "com.github.geirolz" %% "cats-xml-xpath" % "0.0.18"
```

With this module you can create `NodeCursor`s instances using XPath.

Using `NodeCursor` companion object
```scala
import cats.xml.cursor.NodeCursor
import cats.xml.xpath.error.*
import cats.xml.xpath.implicits.*

val cursor: Either[XPathError, NodeCursor] = NodeCursor.fromXPath("/root[@id='1']")
// cursor: Either[XPathError, NodeCursor] = Right(
//   value = /root[filter cats.xml.xpath.CursorBuilder$PredicateBuilder$$$Lambda$11249/0x0000000802d53028@7b3fbbaf]
// )
```

Using string interpolation
```scala
import cats.xml.cursor.NodeCursor
import cats.xml.xpath.error.*
import cats.xml.xpath.implicits.*

val cursor: Either[XPathError, NodeCursor] = xpath"/root[@id='1']"
// cursor: Either[XPathError, NodeCursor] = Right(
//   value = /root[filter cats.xml.xpath.CursorBuilder$PredicateBuilder$$$Lambda$11249/0x0000000802d53028@72be9ffd]
// )
```


Full example

```scala
import cats.xml.XmlNode
import cats.xml.cursor.NodeCursor
import cats.xml.xpath.error.*

import cats.implicits.*
import cats.xml.implicits.*
import cats.xml.xpath.implicits.*

val cursor: Either[XPathError, NodeCursor] = xpath"/root[@id='1']"
// cursor: Either[XPathError, NodeCursor] = Right(
//   value = /root[filter cats.xml.xpath.CursorBuilder$PredicateBuilder$$$Lambda$11249/0x0000000802d53028@dac049]
// )

val data = XmlNode("wrapper").withChildren(
  XmlNode("root").withAttrs("id" := 1)
)
// data: XmlNode.Node = <wrapper>
//  <root id="1"/>
// </wrapper>
val result: Either[Throwable, XmlNode] =
  cursor
    .leftMapThrowable
    .flatMap(_.focus(data).leftMap(_.asException))
// result: Either[Throwable, XmlNode] = Right(value = <root id="1"/>)
```

If you want you can use the xpath expression directly on a `XmlNode` focusing using that xpath

```scala
import cats.xml.XmlNode
import cats.xml.cursor.Cursor

import cats.xml.implicits.*
import cats.xml.xpath.implicits.*

val data = XmlNode("wrapper").withChildren(
  XmlNode("root").withAttrs("id" := 1)
)
// data: XmlNode.Node = <wrapper>
//  <root id="1"/>
// </wrapper>
val result: Cursor.Result[XmlNode] = data.xpath("/root[@id='1']")
// result: Cursor.Result[XmlNode] = Right(value = <root id="1"/>)
```
