# Standard Scala XML support

Add standard scala XML interop support.

```sbt
libraryDependencies += "com.github.geirolz" %% "cats-xml-scalaxml" % "0.0.18"
```

Use

```scala
import cats.xml.scalaxml.implicits.*
```

To have all the conversion method to transform a cats-xml object into std scala xml.
For example, you could convert a `XmlNode` to a `NodeSeq` using `toNodeSeq`

This implicitly transform the `NodeSeq` to `XmlNode`

```scala
import cats.xml.XmlNode

val xmlNode: XmlNode = <Wrapper><Root><Value>100</Value></Root></Wrapper>
// xmlNode: XmlNode = <Wrapper>
//  <Root>
//   <Value>100</Value>
//  </Root>
// </Wrapper>
```
